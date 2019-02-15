package com.binarydistrict.scala.exercise3

object Problem12 {

  sealed trait Cube

  case object OpaqueCube extends Cube

  case object TransparentCube extends Cube

  object Axis {

    case object X extends Axis

    case object Y extends Axis

    case object Z extends Axis

    val All: Seq[Axis] = Seq(X, Y, Z)
  }

  sealed trait Axis

  case class Clearance(axis: Axis, x: Int, y: Int) {
    override def toString: String = s"$axis $x $y"
  }

  def iterateOverAxis(cubes: Seq[Seq[Seq[Cube]]], axis: Axis): Seq[Clearance] = ???

  /*
    Куб состоит из n^3 прозрачных и непрозрачных элементарных кубиков.
    Имеется ли хотя бы один просвет по каждому из трех измерений?
    Если это так, вывести координаты каждого просвета в порядке осей X, Y, Z и по возрастанию координат X и Y.
   */
  def findClearance(cubes: Seq[Seq[Seq[Cube]]]): Seq[Clearance] = {
    require(cubes.size == cubes.maxBy(_.size).size)
    val axisClearances = Axis.All.map(axis => iterateOverAxis(cubes, axis))
    if (axisClearances.forall(_.nonEmpty)) {
      axisClearances.flatten
    } else Seq.empty
  }

  def main(args: Array[String]): Unit = {
    findClearance(
      Seq(
        Seq(
          Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
          Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
          Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube)
        ),
        Seq(
          Seq[Cube](OpaqueCube, TransparentCube, TransparentCube),
          Seq[Cube](TransparentCube, TransparentCube, TransparentCube),
          Seq[Cube](TransparentCube, TransparentCube, OpaqueCube)
        ),
        Seq(
          Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
          Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
          Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube)
        )
      )
    ).foreach(println)
  }
}
