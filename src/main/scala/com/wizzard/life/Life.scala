package com.wizzard.life

class Life(field: Map[Coordinates, Cell]) {

  def isAlive(coord: Coordinates): Boolean = {
    if (field.contains(coord)) {
      field(coord).isAlive
    } else {
      false
    }
  }

  def willSurvive(coord: Coordinates): Boolean = {
    val sum: Integer = neighboursSum(coord.neighbours)
    if (isAlive(coord)) {
      sum > 1 && sum < 4
    } else {
      sum == 3
    }
  }

  def neighboursSum(neighbours: Set[Coordinates]): Integer = {
    def cellAsInt(coord: Coordinates): Integer = {
      if (isAlive(coord)) {
        1
      } else {
        0
      }
    }

    neighbours.foldLeft(0)((sum, coord) => sum + cellAsInt(coord))
  }

  def nextGeneration(epochNum: Integer = 1): Life = {
    if (epochNum < 1) {
      throw new IllegalArgumentException("Incorrect epoch number")
    }

    def liveNeighbours(coords: Set[Coordinates]): Set[(Coordinates, Cell)] = {
      def found = coords.filter(willSurvive).map((_, new Cell(true)))
      found
    }

    val nextGenField = for {
      aliveCell <- field
      neighbours = aliveCell._1.neighbours
      alive <- liveNeighbours(neighbours + aliveCell._1)
    } yield alive

    if (epochNum > 1) {
      new Life(nextGenField).nextGeneration(epochNum - 1)
    } else {
      new Life(nextGenField)
    }
  }

  def mkString : String = {
    field.foldLeft("")((str, entry) => str + entry._1.mkString + "\n")
  }
}
