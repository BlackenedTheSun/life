package com.wizzard.life

class Life(field: Set[Cell]) {

  def getField = field

  def isAlive(cell: Cell): Boolean = {
    field.contains(cell)
  }

  def isAlive(coord: Coordinates): Boolean = {
    isAlive(new Cell(coord, true))
  }

  def willSurvive(cell: Cell): Boolean = {
    val sum: Integer = neighboursSum(cell)
    if (isAlive(cell)) {
      sum > 1 && sum < 4
    } else {
      sum == 3
    }
  }

  def neighboursSum(cell: Cell): Integer = {
    def cellAsInt(cell: Cell): Integer = {
      if (isAlive(cell)) {
        1
      } else {
        0
      }
    }

    neighbours(cell).foldLeft(0)((sum, neighbour) => sum + cellAsInt(neighbour))
  }

  def neighbours(cell: Cell): Set[Cell] = {
    cell.getCoord.neighbours.map(coord => new Cell(coord, isAlive(coord)))
  }

  def mkString : String = {
    field.foldLeft("")((str, cell) => str + cell.getCoord.mkString + "\n")
  }
}

object Life {
  def Life(field: Set[Cell]) = new Life(field)

  def nextGeneration(currGen: Life, epochNum: Integer = 1): Life = {
    if (epochNum < 1) {
      throw new IllegalArgumentException("Incorrect epoch number")
    }

    def filterAliveNextGen(cells: Set[Cell]): Set[Cell] = {
      cells.filter(currGen.willSurvive).map(_.revived)
    }

    val nextGenField = for {
      aliveCell <- currGen.getField
      neighbourCells = currGen.neighbours(aliveCell)
      nextGenCell <- filterAliveNextGen(neighbourCells + aliveCell)
    } yield nextGenCell

    def nextGenLife = Life(nextGenField)
    if (epochNum > 1) {
      nextGeneration(nextGenLife, epochNum - 1)
    } else {
      nextGenLife
    }
  }
}
