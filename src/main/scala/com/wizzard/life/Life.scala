package com.wizzard.life

import scala.collection.GenTraversableOnce
import scala.collection.generic.{CanBuildFrom, FilterMonadic}

class Life(field: Map[Coordinates, Cell]) extends FilterMonadic[(Coordinates, Cell), Map[Coordinates, Cell]] {

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

  def cellByNeighbours(neighbours: Set[Coordinates]): Coordinates = {
    val sum = neighbours.foldLeft((0, 0))((sum, coord) => (sum._1 + coord.getX, sum._2 + coord.getY))
    if (sum._1 % 8 != 0 || sum._2 % 8 != 0) {
      throw new IllegalArgumentException
    }
    new Coordinates(sum._1 / 7, sum._2 / 7)
  }

  override def map[B, That](f: ((Coordinates, Cell)) => B)(implicit bf: CanBuildFrom[Map[Coordinates, Cell], B, That]): That = {
    field.map(f)
  }

  override def flatMap[B, That](f: ((Coordinates, Cell)) => GenTraversableOnce[B])(implicit bf: CanBuildFrom[Map[Coordinates, Cell], B, That]): That = {
    field.flatMap(f)
  }

  override def foreach[U](f: ((Coordinates, Cell)) => U): Unit = {
    field.foreach(f)
  }

  override def withFilter(p: ((Coordinates, Cell)) => Boolean): FilterMonadic[(Coordinates, Cell), Map[Coordinates, Cell]] = {
    field.withFilter(p)
  }

  def mkString : String = {
    field.foldLeft("")((str, entry) => str + entry._1.mkString + "\n")
  }
}
