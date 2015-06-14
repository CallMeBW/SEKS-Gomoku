package model

import control.GameController
import model.Mode.Mode

class Computer(name: String, icon: String, mode:Mode) extends Player(name, icon) {

  def placeNewSymbol(gameController: GameController) = {
    gameController.currentId = (gameController.currentId + 1) % gameController.players.length
    val player = gameController.players(gameController.currentId)
    val tuple = gameController.table.calculateNewEntry(player.lastX, player.lastY, player.icon, mode)
    gameController.placeSymbolOnTable(tuple._1, tuple._2)
  }

}
