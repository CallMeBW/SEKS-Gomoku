package view

import control.GameController

import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.{AnchorPane, GridPane}

class Board(gameController: GameController) extends AnchorPane {

  val gridPane = new GridPane()
  gridPane.setHgap(5)
  gridPane.setVgap(5)

  for (a <- 0 to gameController.table.size - 1) {
    for(b <- 0 to gameController.table.size - 1) {
      val button = new Button{
        text.bind(gameController.table.getEntry(a,b))
        onAction = handle{
          gameController.placeSymbolOnTable(a, b)

        }
      }
      gridPane.children.add(button);
    }
  }



}
