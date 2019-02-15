package com.binarydistrict.scala.exercise3

import scala.collection.mutable
import scala.io.Source
import scala.util.Try

object Problem11 {

  object MenuItem {
    def parse(line: String): Try[MenuItem] = Try {
      val values = line.split(" ")
      val parentId = values(2).toInt
      MenuItem(values(0).toInt, values(1), Some(parentId).filter(_ != 0))
    }
  }

  case class MenuItem(id: Int, title: String, parentId: Option[Int]) {
    override def toString: String = title
  }

  /*
    Дан файл с пунктами меню (id, название, id родителя).
    Если id родителя равно 0, то родителя не существует. Показать полное меню с отступами "-".
   */
  def buildMenu(items: Seq[MenuItem]): Seq[String] = ???

  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(getClass.getResourceAsStream("/exercise3/problem4.txt")).getLines().toList
    val menuItems = lines.flatMap(l => MenuItem.parse(l).toOption)
    buildMenu(menuItems).foreach(println)
  }
}
