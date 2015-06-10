package view


import control.GameController
import main.GomokuApp

import scalafx.scene.control.{Slider, Label, Button, TextField}
import scalafx.scene.layout.AnchorPane
import scalafx.Includes._

class SetupPane(gameController: GameController) extends AnchorPane {

  val welcomeLabel = new Label{
    text = GomokuApp.WELCOME
    layoutX = 250
    layoutY = 50
  }

  val descriptionLabel = new Label{
    text = GomokuApp.WELCOME_MESSAGE
    wrapText = true
    prefWidth <== this.width
    layoutX = 250
    layoutY = 100
  }

  val playerNameOne = new TextField{
    layoutX = 100
    layoutY = 200
  }
  val playerNameTwo = new TextField{
    layoutX = 300
    layoutY = 200
  }
  val sizeSlider = new Slider{
    majorTickUnit = 1
    minorTickCount = 1
    min = 5
    max = 20
    layoutX = 300
    layoutY = 300
  }

  val submit = new Button{
    text = "Submit"
    onAction = handle{
      gameController.createPlayer(0, playerNameOne.text.value)
      gameController.createPlayer(1, playerNameTwo.text.value)
      gameController.createTable(sizeSlider.value.value.toInt)
      gameController.start()
    }
    layoutX = 500
    layoutY = 400
  }

  children add submit
  children add welcomeLabel
  children add playerNameOne
  children add playerNameTwo
  children add sizeSlider


}
