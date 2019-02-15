package com.binarydistrict.scala.exercise2.lecture

object Types1 extends App {

  class Container[T](t: T) {
    override def toString: String = t.toString
  }

  val s = new Container[Int](1)
  println(s)
  val s2 = new Container[String]("1!")
  println(s2)
}
