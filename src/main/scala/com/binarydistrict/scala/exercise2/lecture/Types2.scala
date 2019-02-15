package com.binarydistrict.scala.exercise2.lecture

object Types2 extends App {

  // invariant
  class Container[T](t: T) {
    override def toString: String = t.toString
  }

  //  val s: Container[Animal] = new Container[Dog](new Dog)
  //  println(s)
  val s2: Container[Animal] = new Container[Animal](new Dog)
  println(s2)
}
