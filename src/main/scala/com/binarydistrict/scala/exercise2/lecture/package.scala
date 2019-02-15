package com.binarydistrict.scala.exercise2

package object lecture {

  class Animal {
    def sound: String = "rrr"
  }

  class Dog extends Animal {
    override val sound = "wuf"
  }

  class Cat extends Animal {
    override val sound = "meow"
  }

}
