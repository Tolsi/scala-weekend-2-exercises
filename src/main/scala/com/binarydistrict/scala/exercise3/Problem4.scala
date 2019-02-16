package com.binarydistrict.scala.exercise3

object Problem4 {
  /*
    Верните элементы исходного списка, которые встречаются в нём только 2 раза
   */
  def duplicates[T](s: Seq[T]): Seq[T] = s.groupBy(identity).mapValues(_.size).filter(_._2 == 2).keys.toSeq
}
