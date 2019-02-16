package com.binarydistrict.scala.exercise1

import scala.io.StdIn
import scala.util.Random

object Problem1 {
  /*
    Компьютер загадывает число от 1 до n.
    У пользователя k попыток отгадать.
    Параметры передаются через параметры при запуске приложения.
    После каждой неудачной попытки компьютер сообщает меньше или больше загаданное число сообщениями ("Твоё число больше загаданного" или "Твоё число меньше загаданного").
    В конце игры текст с результатом (или “Ты угадал!”, или “Попытки закончились”).
   */
  def guessGame(n: Int, k: Int): Unit = {
    val randomNumber = 1 + new Random().nextInt(n)
    for (i <- 0 until k) {
      val userNumber = StdIn.readInt()
      if (userNumber == randomNumber) {
        println("Ты угадал!")
        return
      } else if (userNumber > randomNumber) {
        println("Твоё число больше загаданного")
      } else if (userNumber < randomNumber) {
        println("Твоё число меньше загаданного")
      }
    }
    println("Попытки закончились")
  }

  def main(args: Array[String]): Unit = {
    guessGame(args(0).toInt, args(1).toInt)
  }
}
