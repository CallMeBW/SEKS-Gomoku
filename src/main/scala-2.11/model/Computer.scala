package model

import control.GameController
import model.Mode.Mode

class Computer(name: String, icon: String, mode:Mode) extends Player(name, icon) {

  def placeNewSymbol(gameController: GameController) = {
    val player = gameController.players(gameController.currentId + 1 % gameController.players.length)
    val tuple = gameController.table.calculateNewEntry(player.lastX, player.lastY, player.icon, mode)
    gameController.placeSymbolOnTable(tuple._1, tuple._2)
  }

}