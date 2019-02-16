package com.binarydistrict.scala.exercise3

object Problem10 {
  /*
    Рассчитать расстояние Хэмминга между двумя строками
    ( https://ru.wikipedia.org/wiki/%D0%A0%D0%B0%D1%81%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5_%D0%A5%D1%8D%D0%BC%D0%BC%D0%B8%D0%BD%D0%B3%D0%B0 )
    Есть строки разной длины, но нужно бросить IllegalArgument Exception
   */
  def hammingDistance(first: String, second: String): Int = {
    require(first.length == second.length)
    first.zip(second).count(p => p._1 != p._2)
  }
}
