package com.binarydistrict.scala.exercise3

import com.binarydistrict.scala.exercise3.Problem11._
import org.scalatest._

class Exercise11Spec extends FlatSpec with Matchers {
  "Solution" should "output the valid menu" in {
    val menu = Seq(
      MenuItem(1, "A", None),
      MenuItem(2, "B", None),
      MenuItem(3, "C", Some(2)),
      MenuItem(4, "D", None),
      MenuItem(5, "E", Some(3)),
      MenuItem(6, "F", Some(5)),
      MenuItem(7, "G", Some(1)),
      MenuItem(8, "H", Some(2)),
      MenuItem(9, "I", Some(4)),
      MenuItem(10, "K", None),
    )
    buildMenu(menu) should contain theSameElementsInOrderAs Seq("A", "-G", "B", "-C", "--E", "---F", "-H", "D", "-I", "K")
  }
  
  "Solution" should "output the valid menu 2" in {
    val menu = Seq(
      MenuItem(1, "A", None),
      MenuItem(2, "B", None),
      MenuItem(3, "C", Some(2)),
      MenuItem(4, "D", None),
      MenuItem(5, "E", Some(3)),
      MenuItem(6, "F", Some(5)),
      MenuItem(7, "G", Some(1)),
      MenuItem(8, "H", Some(2)),
      MenuItem(9, "I", Some(10)),
      MenuItem(10, "K", None),
    )
    buildMenu(menu) should contain theSameElementsInOrderAs Seq("A", "-G", "B", "-C", "--E", "---F", "-H", "D", "K", "-I")
  }
}
