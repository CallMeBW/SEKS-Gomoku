package view


import javafx.geometry.Pos

import control.GameController
import main.GomokuApp

import scalafx.Includes._
import scalafx.scene.control.{Button, Label, Slider, TextField}
import scalafx.scene.layout.AnchorPane
import scalafx.scene.text.{TextAlignment, Font}

class SetupPane(gameController: GameController) extends AnchorPane {

  val welcomeLabel = new Label {
    text = GomokuApp.WELCOME
    font = new Font(20)
    layoutX = 250
    layoutY = 50
  }

  val descriptionLabel = new Label {
    text = GomokuApp.WELCOME_MESSAGE
    wrapText = true
    textAlignment = TextAlignment.CENTER
    prefWidth = 400
    layoutX = 150
    layoutY = 100
  }

  val playerNameOne = new TextField {
    promptText = GomokuApp.HINT_P1
    layoutX = 150
    layoutY = 200
  }
  val playerNameTwo = new TextField {
    promptText = GomokuApp.HINT_P2
    layoutX = 400
    layoutY = 200
  }
  val sizeSlider = new Slider {
    majorTickUnit = 1
    minorTickCount = 1
    min = 5
    max = 20
    snapToTicks = true

    layoutX = 285
    layoutY = 300
  }

  val gridSizeLabel = new Label {
    sizeSlider.value.addListener{ (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
        text = "grid size: " +  newVal.intValue()
    }
    text = "change grid size"
    textAlignment = TextAlignment.CENTER
    alignment = Pos.CENTER
    prefWidth = 130
    layoutX = 285
    layoutY = 270
  }

  val submit = new Button {
    text = "start game"
    onAction = handle {
      gameController.createPlayer(0, playerNameOne.text.value)
      gameController.createPlayer(1, playerNameTwo.text.value)
      gameController.createTable(sizeSlider.value.value.toInt)
      gameController.start()
    }
    layoutX = 312
    layoutY = 400
  }

  children ++= Seq(submit, welcomeLabel, playerNameOne, playerNameTwo, sizeSlider, descriptionLabel, gridSizeLabel)


}
