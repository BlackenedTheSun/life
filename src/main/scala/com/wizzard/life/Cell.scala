package com.wizzard.life

class Cell(coord: Coordinates, alive: Boolean = false) {
  def isAlive = alive
  def getCoord = coord

  def revived = new Cell(coord, true)

  override def toString: String = {
    s"Cell ${coord.mkString} alive: $alive "
  }


  def canEqual(other: Any): Boolean = other.isInstanceOf[Cell]

  override def equals(other: Any): Boolean = other match {
    case that: Cell =>
      (that canEqual this) &&
        coord.equals(that.getCoord)
    case _ => false
  }

  override def hashCode(): Int = {
    coord.hashCode()
  }
}
