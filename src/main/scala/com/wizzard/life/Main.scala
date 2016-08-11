package com.wizzard.life

import com.wizzard.life.LifeCycle.nextGeneration

object Main {
  def main(args: Array[String]) : Unit = {
    val coords: Map[Coordinates, Cell] = Map(new Coordinates(2, 3) -> new Cell(true),
                                              new Coordinates(3, 3) -> new Cell(true),
                                              new Coordinates(4, 3) -> new Cell(true))
    val newLife = new Life(coords)
    println(newLife.mkString)
    println("".padTo(30, '-'))
    println(nextGeneration(newLife).mkString)
  }
}
