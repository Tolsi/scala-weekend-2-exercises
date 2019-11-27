package com.binarydistrict.scala.exercise3

object Problem4 {
  /*
    Верните элементы исходного списка, которые встречаются в нём только 2 раза
   */
  def duplicates[T](s: Seq[T]): Seq[T] = s.groupBy(identity).toSeq.filter(_._2.length == 2).map(_._1)
}
