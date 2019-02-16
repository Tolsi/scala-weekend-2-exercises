package com.binarydistrict.scala.exercise1

object Problem2 {
  /*
    Вернуть коллекцию "fizz", "buzz", "fizzbuzz" для чисел начиная с 0 (включительно) и n (исключая).
    Для целого натурального числа больше нуля :
      "fizzbuzz" если число делится на 3 и 5
      "buzz" если число делится на 5
      "fizz" если число делится на 3
    Итаче верните само число
    Бросить исключение IllegalArgumentException если n < 1
  */
  def fizzBuzzUntil(n: Int): Seq[String] = {
    require(n >= 1)
    for {
      i <- 0 until n
    } yield {
      i match {
        case i if i % 15 == 0 => "fizzbuzz"
        case i if i % 5 == 0 => "buzz"
        case i if i % 3 == 0 => "fizz"
        case i => i.toString
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println(fizzBuzzUntil(100).mkString(" "))
  }
}
