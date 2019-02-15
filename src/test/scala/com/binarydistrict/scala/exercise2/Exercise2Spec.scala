package com.binarydistrict.scala.exercise2

import com.binarydistrict.scala.exercise2.Problem2.measure
import org.scalatest._

class Exercise2Spec extends FlatSpec with Matchers {
  "Solution" should "measure evaluated time of the passed function" in {
    measure(Thread.sleep(100)) shouldBe 1
    measure(Thread.sleep(1100)) shouldBe 2
  }
}
