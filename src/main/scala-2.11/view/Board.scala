package view

import java.util.{Timer, TimerTask}
import javafx.geometry.Pos

import control.GameController
import main.GomokuApp

import scalafx.Includes._
import scalafx.application.Platform
import scalafx.scene.control.Button
import scalafx.scene.layout.{GridPane, HBox}

class Board(gameController: GameController) extends HBox {
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
              timer.schedule(new TimerTask {
                def run() = Platform.runLater {
                  GomokuApp.setMainPane(GomokuApp.setupPane)
                  timer.cancel()
                }
              }, 3000L)
            }
          }
        }
        gridPane.add(button, a, b)
      }
    }
    gridPane.alignment = Pos.CENTER
    alignment = Pos.CENTER
    GomokuApp.stage.minHeight = prefHeight.value + 200
    GomokuApp.stage.minWidth = prefWidth.value + 10

  }

    children add gridPane
  }