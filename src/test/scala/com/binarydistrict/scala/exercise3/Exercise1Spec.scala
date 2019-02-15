package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem1.differentCount
import org.scalatest._

class Exercise1Spec extends FlatSpec with Matchers {
  "Solution" should "count unique elements of seq" in {
    val seq = Array(1,2,3,4,5,5,5,5,5,5,3,2,1,1)
    differentCount(seq) shouldBe 5
  }
}
