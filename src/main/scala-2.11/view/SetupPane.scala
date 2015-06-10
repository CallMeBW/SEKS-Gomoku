package view


import control.GameController
import main.GomokuApp

import scalafx.scene.control.{Slider, Label, Button, TextField}
import scalafx.scene.layout.AnchorPane
import scalafx.Includes._

class SetupPane(gameController: GameController) extends AnchorPane {
  prefHeight = 250
  prefWidth = 300

  val welcomeLabel = new Label{
    text = GomokuApp.WELCOME
  }
  val playerNameOne = new TextField
  val playerNameTwo = new TextField
  val sizeSlider = new Slider{
    majorTickUnit = 1
    minorTickCount = 1
    min = 5
    max = 20
  }

  val submit = new Button{
    text = "Submit"
    onAction = handle{
      gameController.createPlayer(0, playerNameOne.text.value)
      gameController.createPlayer(1, playerNameTwo.text.value)
      gameController.createTable(sizeSlider.value.value.toInt)
      gameController.start
    }
  }

}
