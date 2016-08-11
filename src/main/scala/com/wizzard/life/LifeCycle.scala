package com.wizzard.life

object LifeCycle {
  def nextGeneration(current: Life): Life = {
    def liveNeighbours(coords: Set[Coordinates]): Set[(Coordinates, Cell)] = {
      def found = coords.filter(current.willSurvive).map((_, new Cell(true)))
      found
    }

    val nextGen = for {
      aliveCell <- current
      neighbours = aliveCell._1.neighbours
      alive <- liveNeighbours(neighbours + aliveCell._1)
    } yield alive

    new Life(nextGen)
  }
}
