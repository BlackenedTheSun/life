package com.wizzard.life

class Cell(alive: Boolean = false) {
  def isAlive = alive

  override def toString: String = {
    isAlive.toString
  }
}
