package com.binarydistrict.scala.exercise3

object Problem6 {
  /*
    Дано предложение.
    Заменить группы пробелов одиночными, крайние пробелы удалить.
    Используйте StringBuilder и if-ы.
   */
  def replace(str: String): String = {
    val lastNonSpaceIndex = str.reverse.takeWhile(_ == ' ').size
    str.dropRight(lastNonSpaceIndex).foldLeft("") {
      case (prev, c) =>
        if (c != ' ' || prev.lastOption.exists(_ != ' ')){
          prev + c
        } else prev
    }
  }

  def main(args: Array[String]): Unit = {
    println(replace("  a    b   b           cccc   "))
  }
}
