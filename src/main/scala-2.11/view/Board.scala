package view

import control.GameController

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.{AnchorPane, GridPane}

class Board(gameController: GameController) extends AnchorPane {
  outer =>

  val gridPane = new GridPane()
  gridPane.setHgap(0)
  gridPane.setVgap(0)

  def init() = {
    gridPane.children.clear()
    for (a <- 0 to gameController.table.size - 1) {
      for (b <- 0 to gameController.table.size - 1) {
        val button = new Button {
          text.bind(gameController.table.getEntry(a, b))
          prefHeight = outer.prefHeight.toInt / (gameController.table.size + 1)
          prefWidth = prefHeight.toInt
          id = "ingamebutton"
          stylesheets add "style.css"
          onAction = handle {
            if (gameController.placeSymbolOnTable(a, b)) {
              gridPane.children.foreach(f => f.disable.set(true))
            }
          }
        }
        gridPane.add(button, a, b)
      }
    }
    gameController.app.stage.resizable = true
    outer.height.addListener { (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
//      gridPane.layoutY = (outer.prefHeight.toInt - gridPane.height.toInt) / 4.0
    }
    outer.width.addListener { (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
      gridPane.layoutX = (outer.width.toInt - gridPane.width.toInt) / 2.0
    }
    gridPane.layoutX = (outer.width.toInt - gridPane.width.toInt) / 2.0
  }

  children add gridPane
}