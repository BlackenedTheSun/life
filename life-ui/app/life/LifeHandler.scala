package life

import com.wizzard.life.{Cell, Coordinates, Life}
import life.dto.{CellDto, LifeDto}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class LifeHandler {
  def handlePost(request: LifeDto): Future[LifeDto] = {
    Future {
      val cells: Set[Cell] = request.aliveCells.map(c => Cell(Coordinates(c.x, c.y)))
      def nextGen = Life.nextGeneration(new Life(cells), request.epochNum)

      def resultCoordinates: Set[CellDto] = nextGen.field.map(c => CellDto(c.coord.x, c.coord.y))
      LifeDto(resultCoordinates)
    }
  }
}
