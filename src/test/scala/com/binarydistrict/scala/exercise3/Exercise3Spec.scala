package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem3.{Credential, top10passwords}
import org.scalatest._

class Exercise3Spec extends FlatSpec with Matchers {
  "Solution" should "pick top 10 passwords" in {
    val testCredentials = Seq(Credential("a", "b"), Credential("a", "c"), Credential("a", "d"), Credential("a", "dd"),
      Credential("a", "cc"), Credential("a", "aa"), Credential("a", "casf"), Credential("a", "caaa"), Credential("a", "cdg"),
      Credential("a", "fdfc"), Credential("a", "fdafc"))
    val testClonedCredentials = testCredentials.zipWithIndex.flatMap {
      case (c, i) => Seq.fill(10 + i)(c)
    }
    val result = top10passwords(testClonedCredentials)
    result should have size 10
    result should contain theSameElementsInOrderAs testCredentials.reverse.take(10).map(_.password)
  }
}
