package control

import main.GomokuApp
import model.Player
import view.Board


class GameController {

  var app: GomokuApp.type = null

  def setGomokuApp(gomApp: GomokuApp.type) = {
    app = gomApp;
  }

  var table: Table = null
  val players = new Array[Player](2)

  def createPlayer(id: Int, name: String) = {
    players(id) = new Player(name, symbols(id))
  }

  def createTable(size: Int) = {
    table = new Table(size)
  }

  def start() = {
    val boardPane = new Board(this)
    ???
  }

  def getPlayers(): Array[Player] = players

  def getTable(): Table = table

}
