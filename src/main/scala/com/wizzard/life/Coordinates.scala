package com.wizzard.life

class Coordinates(_x: Int, _y: Int) {
  lazy val neighbours = Set(up, down, left, right, upLeft, upRight, downLeft, downRight)

  def x = _x
  def y = _y

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
        x == that.x &&
        y == that.y
    case _ => false
  }

}
