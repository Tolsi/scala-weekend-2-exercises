package com.binarydistrict.scala.exercise2

import com.binarydistrict.scala.exercise2.Problem4.fibonacci
import org.scalatest._

class Exercise4Spec extends FlatSpec with Matchers {
  "Solution" should  "compute fibonacci of 0" in {
    fibonacci(1) should equal(0)
  }

  it should "compute fibonacci of 1" in {
    fibonacci(2) should equal(1)
  }

  it should "compute fibonacci of 10" in {
    fibonacci(10) should equal(34)
  }

  it should "throw the illegal argument exception if the number is less that one" in {
    assertThrows[IllegalArgumentException] {
      fibonacci(0)
    }
  }

}
