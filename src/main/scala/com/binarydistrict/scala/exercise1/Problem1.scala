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
  def guessGame(n: Int, k: Int): Unit = ???

  def main(args: Array[String]): Unit = {
    guessGame(args(0).toInt, args(1).toInt)
  }
}
