package com.binarydistrict.scala.exercise3

import scala.io.Source
import scala.util.Try

object Problem3 {

  object Credential {
    def parse(line: String): Try[Credential] = Try {
      val params = line.split(";")
      Credential(params(0), params(1))
    }
  }

  case class Credential(username: String, password: String)

  /*
    Дан файл  с логинами и паролями.
    Найдите топ10 самых популярных паролей.
   */
  def top10passwords(credentials: Seq[Credential]): Seq[String] = {
    credentials.groupBy(_.password).mapValues(_.length).toSeq.sortBy(-_._2).map(_._1).take(10)
  }

  def main(args: Array[String]): Unit = {
    val lines = Source.fromInputStream(getClass.getResourceAsStream("/exercise3/problem3.txt")).getLines().toList
    val credentials = lines.flatMap(l => Credential.parse(l).toOption)
    top10passwords(credentials).foreach(println)
  }
}
