package com.binarydistrict.scala.exercise3

import scala.io.StdIn

object Problem1 {
  /*
    Найти количество различных элементов массива.
    Массив чисел читать из консоли, разделенные пробелами.
    Пример: для 1 4 5 1 1 3 ответ 4.
   */
  def differentCount(array: Array[Int]): Int = array.distinct.length

  def main(args: Array[String]): Unit = {
    println(differentCount(StdIn.readLine().split(" ").map(_.toInt)))
  }
}
