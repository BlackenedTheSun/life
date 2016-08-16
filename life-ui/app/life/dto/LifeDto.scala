package life.dto

case class LifeDto(aliveCells: Set[CellDto], epochNum: Int = 1)
case class CellDto(x: Int, y: Int)
