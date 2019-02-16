package com.binarydistrict.scala.exercise2

object Problem4 {
  /*
    Посчитать N-ое число Фибоначчи.
    f(1) = 0, f(2) = 1, ...
    Если n < 1, то бросить IllegalArgumentException
   */
  def fibonacci(n: Int): Long = {
    require(n >= 1)
    n match {
      case 1 => 0
      case 2 => 1
      case n => fibonacci(n - 1) + fibonacci(n - 2)
    }
  }
}
