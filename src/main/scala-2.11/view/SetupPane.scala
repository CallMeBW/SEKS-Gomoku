package view


import main.GomokuApp

import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout.AnchorPane

class SetupPane extends AnchorPane {
  prefHeight = 250
  prefWidth = 300
  val welcomeLabel = new Label{
    text = GomokuApp.WELCOME
  }
  val playerNameOne = new TextField
  val playerNameTwo = new TextField
}
