package control

import model.Player

class GameController() {

  val table = new Table(10)
  val players = new Array[Player](2)

  def createPlayer(id:Int, name:String) ={
    players(id) = new Player(name, symbols(id))
  }

  def createTable(size:Int) = {

  }

  def start() = {

  }

  def getPlayers(): Array[Player] = players

}
