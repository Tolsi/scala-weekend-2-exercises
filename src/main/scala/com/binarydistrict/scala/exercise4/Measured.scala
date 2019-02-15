package com.binarydistrict.scala.exercise4

trait Measured {
  @volatile private var dummy: Any = _

  protected def timed[T](body: => T): Double = {
    val start = System.nanoTime
    dummy = body
    val end = System.nanoTime
    ((end - start) / 1000) / 1000.0
  }

  protected def warmedTimed[T](times: Int = 200)(body: => T): Double = {
    for (_ <- 0 until times) body
    timed(body)
  }
}
