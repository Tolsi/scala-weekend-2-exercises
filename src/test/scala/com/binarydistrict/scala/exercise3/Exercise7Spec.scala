package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem7.occurrences
import org.scalatest._

class Exercise7Spec extends FlatSpec with Matchers {
  "Solution" should "count occurrences 'aba' in the passed string" in {
    occurrences(" aba dfh;tkweg;awglg aba aba abaaba") shouldBe 5
  }
}
