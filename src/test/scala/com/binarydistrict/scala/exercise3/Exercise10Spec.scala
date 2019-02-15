package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem10.hammingDistance
import org.scalatest._

class Exercise10Spec extends FlatSpec with Matchers {
  "Solution" should "calculate hamming distance" in {
    hammingDistance("aaaaa", "bbbba") shouldBe 4
  }

  it should "throw the illegal argument exception if the sizes of lines are different" in {
    assertThrows[IllegalArgumentException] {
      hammingDistance("aaaaa", "bbbaaa")
    }
  }
}
