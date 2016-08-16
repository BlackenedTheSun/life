package com.wizzard.life

import org.scalacheck.{Arbitrary, Gen, Properties}

object LifeSpec extends Properties("Life"){
  import org.scalacheck.Prop.{forAll, BooleanOperators}
  import Arbitrary.arbitrary

  val emptyLife = Life(Set.empty)
  val stableCoords = Set(Coordinates(1, 2), Coordinates(2, 2), Coordinates(2, 1), Coordinates(1, 1))
  val stableLife = new Life(stableCoords.map(new Cell(_, true)))
  val unstableCoords = Set(Coordinates(1, 2), Coordinates(2, 2), Coordinates(3, 2))
  val unstableLife = new Life(unstableCoords.map(new Cell(_, true)))


  val coordinateGen = for {
    x <- arbitrary[Int]
    y <- arbitrary[Int]
  } yield Coordinates(x, y)

  val coordinateListGen = for {
    size <- Gen.choose(1, 10)
    coord <- Gen.listOfN(size, coordinateGen)
  } yield coord

  val cellGen = for {
    coord <- coordinateGen
  } yield new Cell(coord, true)

  val cellSetGen = for {
    size <- Gen.choose(0, 10)
    cell <- Gen.containerOfN[Set, Cell](size, cellGen)
  } yield cell

  val lifeGen = for {
    cells <- cellSetGen
  } yield new Life(cells)

  property("isAlive cell") = forAll(lifeGen, cellGen) { (life, cell) =>
      life.field.contains(cell) == life.isAlive(cell)
  }

  property("isAlive coordinates") = forAll(lifeGen, coordinateGen) {(life, coordinates) =>
    life.field.contains(new Cell(coordinates, true)) == life.isAlive(coordinates)
  }

  property("neighbours") = forAll(lifeGen, cellGen) { (life, cell) =>
    def neighbours = life.neighbours(cell)
    (neighbours.size == 8) :| "result has 8 cells" &&
      (neighbours.forall(neighbour => life.isAlive(neighbour) == neighbour.isAlive)) :| "result has right alive indicator" &&
      (neighbours.map(_.coord).toList.sortBy(x => x.x + x.y) ==
        cell.coord.neighbours.toList.sortBy(x => x.x + x.y)) :| "result cells has right coordinates"
  }

  property("will survive for empty") = forAll(cellGen) { cell =>
    !emptyLife.willSurvive(cell)
  }

  property("will survive for stable") = forAll(cellGen) { cell =>
    stableCoords.contains(cell.coord) == stableLife.willSurvive(cell)
  }

  property("will survive for unstable") = {
    unstableLife.willSurvive(new Cell(Coordinates(2, 2), true)) &&
    !unstableLife.willSurvive(new Cell(Coordinates(1, 2), true)) &&
    !unstableLife.willSurvive(new Cell(Coordinates(3, 2), true))
  }

  property("nextGeneration of empty") = {
    def nextEmpty = Life.nextGeneration(emptyLife)
    nextEmpty.field.isEmpty
  }

  property("nextGeneration of stable") = {
    Life.nextGeneration(stableLife).field == stableLife.field
  }

  property("nextGeneration of unstable") = {
    Life.nextGeneration(unstableLife).field == Life(Set((2, 1), (2, 2), (2, 3))).field
  }

  property("nextGeneration of unstable twice") = {
    Life.nextGeneration(unstableLife, 2).field == unstableLife.field
  }

  property("nextGeneration of unstable many times") = {
    Life.nextGeneration(unstableLife, 10000).field == unstableLife.field
  }
}
