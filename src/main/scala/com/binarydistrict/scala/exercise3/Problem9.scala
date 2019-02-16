package com.binarydistrict.scala.exercise3

object Problem9 {
  /*
    Сделать бесконечный Iterator последовательных нечетных чисел, начиная с единицы.
   */
  def oddNumbers: Iterator[Int] = Iterator.from(1).filter(_ % 2 == 1)
}
