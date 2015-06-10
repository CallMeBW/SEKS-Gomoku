package view

import control.GameController

import scalafx.scene.control.Button
import scalafx.scene.layout.{Pane, GridPane, AnchorPane}

class Board(gameController: GameController) extends AnchorPane {

  val gridPane = new GridPane();
  gridPane.setHgap(5);
  gridPane.setVgap(5);

  var a = 0;
  for (a <- gameController.table.size) {
    val button = new Button();
    gridPane.children.add(button);
  }



}
