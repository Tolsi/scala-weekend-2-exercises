package com.binarydistrict.scala.exercise2

import com.binarydistrict.scala.exercise2.Problem1._
import org.scalatest._

class Exercise1Spec extends FlatSpec with Matchers {
  "Solution" should "sort men" in {
    val men = Seq(Man("Vasya", 165), Man("Martin", 200), Man("Petya", 185), Man("Micro", 1))
    men.sorted should contain theSameElementsInOrderAs Seq(Man("Micro", 1), Man("Vasya", 165), Man("Petya", 185), Man("Martin", 200))
  }
}
