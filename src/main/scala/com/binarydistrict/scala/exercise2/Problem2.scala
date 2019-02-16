package com.binarydistrict.scala.exercise2

object Problem2 {
  /*
    Сделать функцию, которая возвращает время выполнения переданной ей функции в секундах, с округлением в большую сторону.
  */
  def measure(f: => Unit): Int = {
    val start = System.currentTimeMillis()
    f
    val finish = System.currentTimeMillis()
    1 + ((finish - start) / 1000).toInt
  }
}
