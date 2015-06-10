package control

import model.Player


class GameController() {

  var table:Table = null
  val players = new Array[Player](2)

  def createPlayer(id:Int, name:String) ={
    players(id) = new Player(name, symbols(id))
  }

  def createTable(size:Int) = {
    table = new Table(size)
  }

  def start() = {
      ???
  }

  def getPlayers(): Array[Player] = players

  def getTable():Table = table

}
