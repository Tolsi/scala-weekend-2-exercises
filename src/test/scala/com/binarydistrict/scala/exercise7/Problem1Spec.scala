package com.binarydistrict.scala.exercise7

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import com.binarydistrict.scala.exercise7.Problem1._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._


class Problem1Spec extends WordSpec with Matchers with ScalatestRouteTest with BeforeAndAfterAll {

  "The service" should {

    "return OK for valid POST requests to /book" in {
      Post("/book", Book(1, "a", "b", 1999)) ~> route ~> check {
        status shouldEqual StatusCodes.OK
      }
    }

    "return Conflict for invalid POST requests to /book" in {
      Post("/book", Book(1, "a", "b", 1999)) ~> route ~> check {
        status shouldEqual StatusCodes.Conflict
      }
    }

    "return OK for valid PATCH requests to /book" in {
      Patch("/book", Book(1, "a", "b", 1999)) ~> route ~> check {
        status shouldEqual StatusCodes.OK
      }
    }

    "return NotFound for invalid PATCH requests to /book" in {
      Patch("/book", Book(4, "a", "b", 1999)) ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }

    "return Ok for valid DELETE requests to /book" in {
      Delete("/book?id=1") ~> route ~> check {
        status shouldEqual StatusCodes.OK
      }
    }

    "return NotFound for invalid DELETE requests to /book" in {
      Delete("/book?id=1") ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }

    "return books for valid GET requests to /book" in {
      Get("/book") ~> route ~> check {
        entityAs[Seq[Book]] should contain theSameElementsAs books
      }
    }

    "return books with id filter for valid GET requests to /book" in {
      Get("/book?id=100") ~> route ~> check {
        entityAs[Seq[Book]] should contain theSameElementsAs Seq(Book(100, "a", "b", 2010))
      }
    }

    "return books with author filter for valid GET requests to /book" in {
      Get("/book?author=c") ~> route ~> check {
        entityAs[Seq[Book]] should contain theSameElementsAs Seq(Book(101, "c", "bb", 2011), Book(102, "c", "bbb", 2011))
      }
    }

    "return books with name filter for valid GET requests to /book" in {
      Get("/book?title=b") ~> route ~> check {
        entityAs[Seq[Book]] should contain theSameElementsAs books
      }
    }

    "return books with year filter for valid GET requests to /book" in {
      Get("/book?year=2011") ~> route ~> check {
        entityAs[Seq[Book]] should contain theSameElementsAs Seq(Book(101, "c", "bb", 2011), Book(102, "c", "bbb", 2011))
      }
    }

    "return NotFound with any filter with empty result for GET requests to /book" in {
      Get("/book?id=1004") ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
      }
      Get("/book?author=crg") ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
      }
      Get("/book?title=b1") ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
      }
      Get("/book?year=20") ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }
  }

  private val books = Seq(
    Book(100, "a", "b", 2010),
    Book(101, "c", "bb", 2011),
    Book(102, "c", "bbb", 2011)
  )

  override protected def beforeAll(): Unit = {
    books.foreach(b => Post("/book", b) ~> route)
  }
}
