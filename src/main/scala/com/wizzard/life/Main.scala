package com.wizzard.life

object Main {
  def main(args: Array[String]) : Unit = {
    val coords: Set[Cell] = Set(new Cell(new Coordinates(2, 3), true),
                                              new Cell(new Coordinates(3, 3), true),
                                              new Cell(new Coordinates(4, 3), true),
      new Cell(new Coordinates(5, 3), true))
    val newLife = new Life(coords)
    println(newLife.mkString)
    println("".padTo(30, '-'))
    println(Life.nextGeneration(newLife, 10000).mkString)
  }
}
