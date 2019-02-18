package com.binarydistrict.scala.exercise5

import org.scalatest._

class Exercise1Spec extends FlatSpec with Matchers {
  "Solution" should "output correct word for 211" in {
    Pluralization.pluralizeProgrammer(211) shouldBe "211 программистов"
  }

  it should "output correct word for 514" in {
    Pluralization.pluralizeProgrammer(514) shouldBe "514 программистов"
  }

  it should "output correct word for 1" in {
    Pluralization.pluralizeProgrammer(1) shouldBe "1 программист"
  }

  it should "output correct word for 2" in {
    Pluralization.pluralizeProgrammer(2) shouldBe "2 программиста"
  }
}
