package com.binarydistrict.scala.exercise3

import scala.util.Random

object Problem8 {
  /*
    Заполните массив случайным образом нулями и единицами так, чтобы количество единиц было больше количества нулей.
   */
  def fillMoreOnes(n: Int): Array[Int] = {
    val result = Array.fill(n)(1)
    val zeros = n / 2 + 1
    val r = new Random()
    for {i <- 0 until zeros} {
      result(r.nextInt(n)) = 0
    }
    result
  }
}
