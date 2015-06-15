package control

import java.util.{TimerTask, Timer}

import main.GomokuApp
import model.Mode.Mode
import model.{Computer, Player}

import scalafx.application.Platform


class GameController {

  var table: Table = null
  val players = new Array[Player](2)
  var current: Player = null
  var currentId = -1

  def createPlayer(id: Int, name: String) = {
    players(id) = new Player(name, symbols(id))
  }

  def createComputer(id: Int, mode: Mode): Unit = {
    players(id) = new Computer("Computer", symbols(id), mode)
  }

  def createTable(size: Int) = {
    table = new Table(size)
  }

  def placeSymbolOnTable(x: Int, y: Int): Boolean = {
    if (current.placeSymbolOnTable(table, x, y)) {
      if (table.winTest(x, y, current.icon)) {
        println(x + " / " + y + " / " + current.icon)
        GomokuApp.statusPane.setStatus(current.WON)
        true
      } else if (table.checkForTie()) {
        println("TIE")
        GomokuApp.statusPane.setStatus("It's a tie!")
        val timer = new Timer()
        timer.schedule(new TimerTask{
          def run() = Platform.runLater {GomokuApp.setMainPane(GomokuApp.setupPane)}
        },3000L)
        false
      } else {
        currentId = (currentId + 1) % players.length
        current = players(currentId)
        GomokuApp.statusPane.statusLabel.text.set(current.ROUND)
        GomokuApp.statusPane.setStatus(current.ROUND)
        current match {
          case comp: Computer =>
            comp.placeNewSymbol(this)
            false
          case player: Player =>
            false
        }
      }
    } else {
      false
    }
  }

  def start() = {
    currentId = 0
    current = players(currentId)
    GomokuApp.setMainPane(GomokuApp.boardPane)
    GomokuApp.boardPane.init()
  }

}
