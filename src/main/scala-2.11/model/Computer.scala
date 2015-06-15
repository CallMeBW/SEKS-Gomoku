package model

import control.GameController
import model.Mode.Mode

class Computer(name: String, icon: String, mode:Mode) extends Player(name, icon) {

  def placeNewSymbol(gameController: GameController):Boolean = {
    val player = gameController.players((gameController.currentId + 1) % gameController.players.length)
    var tuple = gameController.table.calculateNewEntry(player.lastX, player.lastY, player.icon, mode, player)
    if(tuple._1 == -1 || tuple._2 == -1){
      tuple = gameController.table.calculateNewEntry(lastX, lastY, icon, mode, this)
    }
    gameController.placeSymbolOnTable(tuple._1, tuple._2)
  }

}
