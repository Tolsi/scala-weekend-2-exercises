package com.binarydistrict.scala.exercise7

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn


object Problem1 {

  case class Book(id: Int, author: String, title: String, year: Int)

  implicit val bookFormat = jsonFormat4(Book)

  /*
    Сделать API для работы с каталогом книг. Про книгу известно: уникальный номер, автор, название, год издания.
    Реализовать CRUD, показ всех книг на экран и поиск по каждому из полей.
    POST /book {id, author, title, year} - добавить книгу. 409, если она уже есть
    DELETE /book?id - удалить книгу. 404 если её нет
    PATCH /book {id, author, title, year} - обновить книгу. 404, если её еще нет
    GET /book?id,author,title,year - найти книги по частичному совпадению (contains) одного из параметров или без них.
                  Для простоты можно считать, что одновременно может использоваться фильтр только по одному параметру.
                  Если ничего не нашлось, то 404.
    Данные необходимо хранить локально.
   */
  val route: Route = ???

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ ⇒ system.terminate()) // and shutdown when done
  }
}
