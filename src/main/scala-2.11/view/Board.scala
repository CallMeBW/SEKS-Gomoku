package view

import control.GameController

import scalafx.scene.control.Button
import scalafx.scene.layout.{Pane, GridPane, AnchorPane}
import scalafx.Includes._

class Board(gameController: GameController) extends AnchorPane {

  val gridPane = new GridPane();
  gridPane.setHgap(5);
  gridPane.setVgap(5);

  for (a <- gameController.table.size) {
    for(b <- gameController.table.size) {
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
