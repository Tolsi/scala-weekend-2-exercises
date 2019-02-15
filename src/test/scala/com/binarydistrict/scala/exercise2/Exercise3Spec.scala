package com.binarydistrict.scala.exercise2

import com.binarydistrict.scala.exercise2.Problem3.applyFor1to10
import org.scalatest._

class Exercise3Spec extends FlatSpec with Matchers {
  "Solution" should "apply the passed function to the number seq" in {
    applyFor1to10(_ + 1) shouldBe Seq(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
  }
}
