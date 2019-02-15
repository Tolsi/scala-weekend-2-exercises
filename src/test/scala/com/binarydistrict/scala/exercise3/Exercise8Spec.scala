package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem8.fillMoreOnes
import org.scalatest._

class Exercise8Spec extends FlatSpec with Matchers {
  "Solution" should "generate array with ones more than zeros" in {
    val result = fillMoreOnes(10)
    result should have size 10
    result.count(_ == 1) should be > result.count(_ == 0)
  }
}
