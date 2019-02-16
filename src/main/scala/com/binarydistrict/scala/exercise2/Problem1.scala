package com.binarydistrict.scala.exercise2

object Problem1 {

  case class Man(name: String, height: Int)

  /*
    Реализовать implicit объект Ordering, чтобы работала сортировка с помощью sorted
   */
  implicit val menOrdering: Ordering[Man] = Ordering.by(_.height)

  def main(args: Array[String]): Unit = {
    println(Seq(Man("Vasya", 165), Man("Martin", 200), Man("Petya", 185)).sorted)
  }
}
