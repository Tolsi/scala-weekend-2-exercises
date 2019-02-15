package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem2.generateSchedule
import org.joda.time.LocalDate
import org.scalatest._

class Exercise2Spec extends FlatSpec with Matchers {
  "Solution" should "create valid schedule" in {
    val correctAnswerDatesString = "2019-02-15, 2019-02-18, 2019-02-19, 2019-02-21, 2019-02-23, 2019-02-25, 2019-02-27, 2019-03-01, 2019-03-04, 2019-03-05, 2019-03-07, 2019-03-09, 2019-03-11, 2019-03-13, 2019-03-15, 2019-03-18, 2019-03-19, 2019-03-21, 2019-03-23, 2019-03-25, 2019-03-27, 2019-03-29, 2019-04-01, 2019-04-02, 2019-04-04, 2019-04-06, 2019-04-08, 2019-04-10, 2019-04-12, 2019-04-15"
    val correctAnswerDates = correctAnswerDatesString.split(", ").map(LocalDate.parse)
    val schedule = generateSchedule(correctAnswerDates.head)
    schedule should have size(30)
    schedule should contain theSameElementsInOrderAs(correctAnswerDates)
  }
}
