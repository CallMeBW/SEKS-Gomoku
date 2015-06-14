package control

import main.GomokuApp
import model.Mode.Mode
import model.{Computer, Player}


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

  def createComputer(id:Int, mode:Mode): Unit = {
    players(id) = new Computer("cpu",symbols(id), mode)
  }

  def createTable(size: Int) = {
    table = new Table(size)
  }

  def placeSymbolOnTable(x:Int, y:Int):Boolean = {
    if(current.placeSymbolOnTable(table, x,y )){
      if(table.winTest(x, y, current.icon)) {
        app.statusPane.setStatus(current.WON)
        app.setMainPane(app.setupPane)
        true
      } else if(table.checkForTie(){
        // TODO
      } else {
        currentId = (currentId + 1) % players.length
        current = players(currentId)
        app.statusPane.statusLabel.text.set(current.ROUND)
        app.statusPane.setStatus(current.ROUND)
        current match {
          case comp:Computer => comp.placeNewSymbol(this)
        }
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
