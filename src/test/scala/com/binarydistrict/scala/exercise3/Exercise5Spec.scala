package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem5.replace
import org.scalatest._

class Exercise5Spec extends FlatSpec with Matchers {
  "Solution" should "replace the many spaces with one and trim the string" in {
    replace("  a a  a bb bbb     c df d   ") shouldBe("a a a bb bbb c df d")
  }
}
