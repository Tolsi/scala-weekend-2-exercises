package com.binarydistrict.scala.exercise3

import org.joda.time.{DateTimeConstants, LocalDate}

object Problem2 {
  def daysRange(from: LocalDate): Stream[LocalDate] = from #:: daysRange(from.plusDays(1))

  /*
    Уроки по математике проводятся через день.
    Если урок попадает на воскресенье, то переносится на понедельник.
    Пользователь вводит дату первого урока.
    Сгенерируйте расписание на 30 занятий вперед.
   */
  def generateSchedule(startDate: LocalDate): Seq[LocalDate] = {
    daysRange(startDate).zipWithIndex.filter(_._2 % 2 == 0).take(30).map {
      case (date, i) => if (date.dayOfWeek().get() == DateTimeConstants.SUNDAY) {
        date.plusDays(1)
      } else {
        date
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println(generateSchedule(LocalDate.now()).mkString(", "))
  }
}
