package com.binarydistrict.scala.exercise8

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import scalikejdbc._
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Problem1 extends Directives with SprayJsonSupport with DefaultJsonProtocol {

  /*
    Реализовать API для своего Twitter-а
    POST /user                     json{username, session_id}                    - регистрация пользователя. session_id уникальный, по нему мы можем найти пользователя. Возвращает просто 200 или 409, если пользователи или session_id уже есть
    POST /post                     json{message, session_id}                     - отправить сообщение, возвращает message_id сообщения. В сообщении не должно быть больше 140 символов, иначе надо бросить код 400.
    DELETE /post                   json{session_id, message_id}                  - удалить сообщение. 404, если нет. 403, если session_id не принадлежит пользователю
    GET /post/{username}           [json{message, username}]                     - получить сообщения пользователя. 404, если его нет
    GET /post/{username}/page/{n}  [json{message, username}]                     - получить сообщения пользователя по страницам по 3 сообщения на страницу в порядке отправления (старые сообщения идут раньше). 404, если его нет или страницы
    GET /stats                     [words_count, messages_count, users_count]    - получить статистику.
   */

  case class User(username: String, sessionId: String)

  case class Post(username: String, message: String)
  case class PostRequest(sessionId: String, message: String)

  case class DeleteMessageRequest(messageId: Long, sessionId: String)

  case class Statistics(wordsCount: Int, messagesCount: Int, usersCount: Int)

  implicit val postFormat = jsonFormat2(Post)

  val repo: TwitterRepository = ???

  trait TwitterRepository {
    def createUser(user: User): Future[Boolean]

    def checkSenderOfMessage(messageId: Long, sessionId: String): Future[Boolean]

    def createPost(postRequest: PostRequest): Future[Boolean]

    def deletePost(messageId: Long): Future[Boolean]

    def findPost(username: String, page: Option[Int] = None): Future[Seq[Post]]

    def stats(): Future[Statistics]
  }

  val route: Route = {
    path("user") {
      post {
        entity(as[User]) { user =>
          complete {
            repo.createUser(user) map {
              case true => StatusCodes.OK
              case false => StatusCodes.BadRequest
            }
          }
        }
      }
    } ~ path("post" / Segment) { username =>
      post {
        entity(as[PostRequest]) { postRequest =>
          complete {
            repo.createPost(postRequest) map {
              case true => StatusCodes.OK
              case false => StatusCodes.Forbidden
            }
          }
        }
      } ~ path("page" / IntNumber) { page =>
        get {
          complete {
            repo.findPost(username, Some(page))
          }
        }
      } ~ delete {
        entity(as[DeleteMessageRequest]) { request =>
          complete {
            repo.checkSenderOfMessage(request.messageId, request.sessionId).flatMap(result =>
              if (result) {
                repo.deletePost(request.messageId) map {
                  case true => StatusCodes.OK
                  case false => StatusCodes.InternalServerError
                }
              } else {
                Future.successful(StatusCodes.Forbidden)
              }
            )
          }
        }
      } ~ get {
        complete {
          repo.findPost(username)
        }
      }
    } ~ path("stats") {
      get {
        ???
      }
    }
  }


  def initDb()(implicit session: DBSession = AutoSession): Unit = {
    // table creation
    sql"""
        create table users (
          username varchar(12) not null unique,
          session_id varchar(12) not null unique
        );
        create table posts (
          id bigint auto_increment not null primary key,
          username varchar(12) not null,
          message text not null
        );
    """.execute.apply()
  }

  def main(args: Array[String]): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:example;DB_CLOSE_DELAY=-1", "", "")

    DB.autoCommit(session => {
      initDb()
    })


  }
}
