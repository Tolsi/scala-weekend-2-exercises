package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem12.Axis._
import com.binarydistrict.scala.exercise3.Problem12._
import org.scalatest._

class Exercise12Spec extends FlatSpec with Matchers {
  "Solution" should "output all the clearances if there're at least 1 clearance on every of axes" in {
    val cubes = Seq(
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

    findClearance(cubes) should contain theSameElementsInOrderAs Seq(
      Clearance(X, 0, 1),
      Clearance(X, 1, 1),
      Clearance(X, 2, 1),
      Clearance(Y, 0, 1),
      Clearance(Y, 1, 1),
      Clearance(Y, 2, 1),
      Clearance(Z, 1, 1)
    )
  }

  "Solution" should "output nothing if there aren't at least 1 clearance on every of axes" in {
    val cubes = Seq(
      Seq(
        Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
        Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
        Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube)
      ),
      Seq(
        Seq[Cube](OpaqueCube, TransparentCube, TransparentCube),
        Seq[Cube](TransparentCube, OpaqueCube, TransparentCube),
        Seq[Cube](TransparentCube, TransparentCube, OpaqueCube)
      ),
      Seq(
        Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
        Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube),
        Seq[Cube](OpaqueCube, TransparentCube, OpaqueCube)
      )
    )

    findClearance(cubes) shouldBe 'empty
  }
}
