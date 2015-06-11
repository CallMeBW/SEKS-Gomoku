package view

import control.GameController

import scalafx.Includes._
import scalafx.beans.property.StringProperty
import scalafx.scene.control.{Label, Button}
import scalafx.scene.layout.{Pane, AnchorPane, GridPane}

class Board(gameController: GameController) extends AnchorPane {

  val gridPane = new GridPane()
  gridPane.setHgap(1)
  gridPane.setVgap(1)

  def init() : Unit = {
    for (a <- 0 to gameController.table.size - 1) {
      for(b <- 0 to gameController.table.size - 1) {
        val button = new Button{
          text = gameController.table.getEntry(a, b).value

          onAction = handle{
            gameController.placeSymbolOnTable(a, b)
            text = gameController.table.getEntry(a, b).value
            println(gameController.table.winTest(a, b, gameController.current.icon))
          }
        }
        gridPane.add(button, a, b)
      }
    }
  }
  children add gridPane
}