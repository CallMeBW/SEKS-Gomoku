package view

import control.GameController

import scalafx.scene.layout.{Pane, GridPane, AnchorPane}

class Board(gameController: GameController) extends AnchorPane {

  val gridPane = new GridPane();
  gridPane.setHgap(5);
  gridPane.setVgap(5);




}
