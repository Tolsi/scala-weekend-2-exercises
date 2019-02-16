package com.binarydistrict.scala.exercise3

object Problem7 {
  /*
    Найдите количество вхождения 'aba' в строку.
  */
  def occurrences(str: String): Int = str.sliding(3).count(_ == "aba")

  def main(args: Array[String]): Unit = {
    println(occurrences("ababa"))
  }
}
