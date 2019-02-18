package com.binarydistrict.scala.exercise7

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn


object Problem1 extends Directives with SprayJsonSupport with DefaultJsonProtocol {

  case class Book(id: Int, author: String, title: String, year: Int)

  implicit val bookFormat = jsonFormat4(Book)

  private val repository: BookRepository = new MapBookRepository()

  trait BookRepository {
    def filter(idOpt: Option[Int], authorOpt: Option[String], titleOpt: Option[String], yearOpt: Option[Int]): Future[Seq[Book]]
    def insert(book: Book): Future[Boolean]
    def update(book: Book): Future[Boolean]
    def delete(id: Int): Future[Boolean]
  }

  class SqlBookRepository extends BookRepository {
    override def filter(idOpt: Option[Int], authorOpt: Option[String], titleOpt: Option[String], yearOpt: Option[Int]): Future[Seq[Book]] = ???

    override def insert(book: Book): Future[Boolean] = ???

    override def update(book: Book): Future[Boolean] = ???

    override def delete(id: Int): Future[Boolean] = ???
  }

  class MapBookRepository extends BookRepository {
    private val books = new mutable.HashMap[Int, Book]()

    def filter(idOpt: Option[Int], authorOpt: Option[String], titleOpt: Option[String], yearOpt: Option[Int]): Future[Seq[Book]] = Future {
      (idOpt, authorOpt, titleOpt, yearOpt) match {
        case (Some(id), _, _, _) => books.get(id).toSeq
        case (_, Some(author), _, _) => books.values.filter(_.author.contains(author)).toSeq
        case (_, _, Some(title), _) => books.values.filter(_.title.contains(title)).toSeq
        case (_, _, _, Some(year)) => books.values.filter(_.year == year).toSeq
        case _ => books.values.toSeq
      }
    }
    def insert(book: Book): Future[Boolean] = Future {
      val result = !books.contains(book.id)
      if (result) {
        books.put(book.id, book)
      }
      result
    }
    def update(book: Book): Future[Boolean] = Future {
      val result = books.contains(book.id)
      if (result) {
        books.put(book.id, book)
      }
      result
    }
    def delete(id: Int): Future[Boolean] = Future {
      val result = books.contains(id)
      if (result) {
        books.remove(id)
      }
      result
    }
  }

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
  val route: Route = {
    pathPrefix("book") {
      post {
        entity(as[Book]) { book =>
          complete {
            repository.insert(book).map {
              case true => StatusCodes.OK
              case false => StatusCodes.Conflict
            }
          }
        }
      } ~ get {
        parameters('id.as[Int] ?, 'author ?, 'title ?, 'year.as[Int] ?) { (idOpt, authorOpt, titleOpt, yearOpt) =>
          complete {
            repository.filter(idOpt, authorOpt, titleOpt, yearOpt).map[ToResponseMarshallable] { found =>
              if (found.nonEmpty) {
                found
              } else {
                StatusCodes.NotFound
              }
            }
          }
        }
      } ~ patch {
        entity(as[Book]) { book =>
          complete {
            repository.update(book).map {
              case true => StatusCodes.OK
              case false => StatusCodes.NotFound
            }
          }
        }
      } ~ delete {
        parameter('id.as[Int]) { id =>
          complete {
            repository.delete(id).map {
              case true => StatusCodes.OK
              case false => StatusCodes.NotFound
            }
          }
        }
      }
    }
  }

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
