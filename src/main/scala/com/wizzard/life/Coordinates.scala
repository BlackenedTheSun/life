package com.wizzard.life

class Coordinates(x: Int, y: Int) {
  lazy val neighbours = Set(up, down, left, right, upLeft, upRight, downLeft, downRight)

  def getX = x
  def getY = y

  val z = 0

  def up = new Coordinates(x, y + 1)
  def down = new Coordinates(x, y - 1)
  def left = new Coordinates(x - 1, y)
  def right = new Coordinates(x + 1, y)
  def upLeft = new Coordinates(x - 1, y + 1)
  def upRight = new Coordinates(x + 1, y + 1)
  def downLeft = new Coordinates(x - 1, y - 1)
  def downRight = new Coordinates(x + 1, y - 1)

  def mkString : String = {
    s"[$x, $y] "
  }

  override def toString: String = {
    mkString
  }

  override def hashCode(): Int = {
    x * 31 + y
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Coordinates]

  override def equals(other: Any): Boolean = other match {
    case that: Coordinates =>
      (that canEqual this) &&
        x == that.getX &&
        y == that.getY
    case _ => false
  }

}
