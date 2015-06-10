package control

import main.GomokuApp
import model.Player
import view.Board


class GameController {
  def placeSymbolOnTable(x:Int, y:Int) = ???


  var app: GomokuApp.type = null
  var table: Table = null
  val players = new Array[Player](2)
  var current:Player = null

  def setGomokuApp(gomApp: GomokuApp.type) = {
    app = gomApp;
  }

  def createPlayer(id: Int, name: String) = {
    players(id) = new Player(name, symbols(id))
  }

  def createTable(size: Int) = {
    table = new Table(size)
  }

  def start() = {
    current = players(0)
    val boardPane = new Board(this)

    ???
  }

}
