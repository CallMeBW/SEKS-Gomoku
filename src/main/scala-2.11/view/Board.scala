package view

import java.util.{TimerTask, Timer}

import control.GameController
import main.GomokuApp

import scalafx.Includes._
import scalafx.application.Platform
import scalafx.beans.Observable
import scalafx.scene.control.Button
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.scene.paint.Color

class Board(gameController: GameController) extends AnchorPane {
  outer =>
  id = "boardpane"
  stylesheets add "style.css"
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
              val timer = new Timer()
              timer.schedule(new TimerTask{
                def run() = Platform.runLater {GomokuApp.setMainPane(GomokuApp.setupPane)}
              },3000L)

            }
          }
        }
        gridPane.add(button, a, b)
      }
    }
    outer.height.onInvalidate { op: Observable =>
      gridPane.layoutY = (outer.height.toInt - gridPane.height.toInt) / 2.0
    }
    outer.width.onInvalidate {
      gridPane.layoutX = (outer.width.toInt - gridPane.width.toInt) / 2.0
    }

    gridPane.layoutX = (outer.prefWidth.toInt - gridPane.width.toInt) / 2.0
    gridPane.layoutY = (outer.prefHeight.toInt - gridPane.height.toInt) / 2.0

    gridPane.width.onInvalidate { op: Observable =>
      GomokuApp.stage.minWidth = gridPane.width.value + 100
    }
    gridPane.height.onInvalidate { op: Observable =>
      GomokuApp.stage.minHeight = gridPane.height.value + 100
    }


  }

  children add gridPane
}