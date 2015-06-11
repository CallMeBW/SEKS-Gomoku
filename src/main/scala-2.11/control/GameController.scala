package control

import main.GomokuApp
import model.Player


class GameController {

  var app: GomokuApp.type = null
  var table: Table = null
  val players = new Array[Player](2)
  var current:Player = null
  var currentId = -1

  def setGomokuApp(gomApp: GomokuApp.type) = {
    app = gomApp
  }

  def createPlayer(id: Int, name: String) = {
    players(id) = new Player(name, symbols(id))
  }

  def createTable(size: Int) = {
    table = new Table(size)
  }

  def placeSymbolOnTable(x:Int, y:Int):Boolean = {
    if(current.placeSymbolOnTable(table, x,y )){
      if(table.winTest(x, y, current.icon)) {
        app.statusPane.setStatus(current.WON)
        true
      } else {
        currentId = (currentId + 1) % players.length
        current = players(currentId)
        app.statusPane.statusLabel.text.set(current.ROUND)
        app.statusPane.setStatus(current.ROUND)
        false
      }
    } else {
      false
    }
  }

  def start() = {
    currentId = 0
    current = players(currentId)
    app.setMainPane(app.boardPane)
    app.boardPane.init()
  }

}
