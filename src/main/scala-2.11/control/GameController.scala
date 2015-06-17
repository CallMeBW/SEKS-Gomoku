package control

import java.util.{Timer, TimerTask}

import main.GomokuApp
import model.Mode.Mode
import model.{Computer, Player}

import scalafx.application.Platform


class GameController {

  var table: Table = null
  val players = new Array[Player](2)
  var current: Player = null
  var currentId = -1

  def createPlayer(id: Int, name: String, symbol: String) = {
    players(id) = new Player(name,if (symbol.length == 1) symbol else symbols(id))
  }

  def createComputer(id: Int, mode: Mode, symbol: String): Unit = {
    players(id) = new Computer("Computer", if (symbol.length == 1) symbol else symbols(id), mode)
  }

  def createTable(size: Int) = {
    table = new Table(size)
  }

  def placeSymbolOnTable(x: Int, y: Int): Boolean = {
    if (current.placeSymbolOnTable(table, x, y)) {
      if (table.winTest(x, y, current.icon)) {
        GomokuApp.statusPane.setStatus(current.WON)
        true
      } else if (table.checkForTie()) {
        GomokuApp.statusPane.setStatus("It's a tie!")
        val timer = new Timer()
        timer.schedule(new TimerTask {
          def run() = Platform.runLater {
            GomokuApp.setMainPane(GomokuApp.setupPane)
            timer.cancel()
          }
        }, 3000L)
        false
      } else {
        currentId = (currentId + 1) % players.length
        current = players(currentId)
        GomokuApp.statusPane.statusLabel.text.set(current.ROUND)
        GomokuApp.statusPane.setStatus(current.ROUND)
        current match {
          case comp: Computer =>
            comp.placeNewSymbol(this)
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
    GomokuApp.statusPane.statusLabel.text.set(current.ROUND)
    GomokuApp.boardPane.init()
    GomokuApp.setMainPane(GomokuApp.boardPane)
  }

}
