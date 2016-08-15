package com.wizzard.life

object Main {
  def main(args: Array[String]) : Unit = {
    val coords: Set[(Int, Int)] = Set((2, 3), (3, 3), (4, 3), (5, 3))
    val newLife = Life(coords)
    println(newLife.mkString)
    println("".padTo(30, '-'))
    println(Life.nextGeneration(newLife, 10000).mkString)
  }
}
