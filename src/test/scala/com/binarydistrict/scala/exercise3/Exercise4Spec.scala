package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem4.duplicates
import org.scalatest._

class Exercise4Spec extends FlatSpec with Matchers {
  "Solution" should "find duplicates" in {
    duplicates(Seq("a", "a", "b", "c", "e", "g", "c", "e", "c")) should contain theSameElementsAs Seq("a", "e")
  }
}
