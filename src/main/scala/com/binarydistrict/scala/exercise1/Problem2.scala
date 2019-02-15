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
  def fizzBuzzUntil(n: Int): Seq[String] = ???

  def main(args: Array[String]): Unit = {
    println(fizzBuzzUntil(100).mkString(" "))
  }
}
