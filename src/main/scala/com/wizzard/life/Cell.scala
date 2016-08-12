package com.wizzard.life

class Cell(_coord: Coordinates, _alive: Boolean = false) {
  def isAlive = _alive
  def coord = _coord

  def revived = new Cell(coord, true)

  override def toString: String = {
    s"Cell ${coord.mkString} alive: $isAlive "
  }


  def canEqual(other: Any): Boolean = other.isInstanceOf[Cell]

  override def equals(other: Any): Boolean = other match {
    case that: Cell =>
      (that canEqual this) &&
        coord.equals(that.coord)
    case _ => false
  }

  override def hashCode(): Int = {
    coord.hashCode()
  }
}
