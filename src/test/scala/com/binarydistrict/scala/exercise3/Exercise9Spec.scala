package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem9.oddNumbers
import org.scalatest._

class Exercise9Spec extends FlatSpec with Matchers {
  "Solution" should "generate infinite iterator of odd numbers" in {
    oddNumbers.take(100000) forall (_ % 2 == 1) shouldBe true
  }
}
