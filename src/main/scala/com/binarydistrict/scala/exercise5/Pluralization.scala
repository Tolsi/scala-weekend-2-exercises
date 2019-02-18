package com.binarydistrict.scala.exercise5

object Pluralization {
  /*
    Вернуть строку "N программистов" в правильном склонении - "1 программист", "4352 программистa", "45 программистов".
   */
  def pluralizeProgrammer(n: Int): String = {
    s"$n программист${
      if (n % 10 == 1) {
        ""
      } else if (Set(2, 3, 4).contains(n % 10)) {
        "a"
      } else {
        "ов"
      }
    }"
  }

  def main(args: Array[String]): Unit = {
    println(pluralizeProgrammer(18))
  }
}
