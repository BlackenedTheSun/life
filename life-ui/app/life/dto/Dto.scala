package life.dto

class Dto
case class LifeDto(aliveCells: Set[CellDto], epochNum: Int = 1) extends Dto
case class CellDto(x: Int, y: Int) extends Dto
