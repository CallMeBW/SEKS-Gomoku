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
    layoutY = 30
  }

  val descriptionLabel = new Label {
    text = GomokuApp.WELCOME_MESSAGE
    wrapText = true
    textAlignment = TextAlignment.CENTER
    prefWidth = 400
    layoutX = 150
    layoutY = 80
  }

  val p1TextField = new TextField {
    promptText = GomokuApp.HINT_P1
    layoutX = 170
    layoutY = 190
  }
  val p2TextField = new TextField {
    promptText = GomokuApp.HINT_P2
    layoutX = 380
    layoutY = 190
  }
  val sizeSlider = new Slider {
    majorTickUnit = 1
    minorTickCount = 1
    min = 5
    max = 20
    snapToTicks = true
    value = 17
    layoutX = 285
    layoutY = 300
  }

  val gridSizeLabel = new Label {
    sizeSlider.value.addListener{ (o: javafx.beans.value.ObservableValue[_ <: Number], oldVal: Number, newVal: Number) =>
        text = "grid size: " +  newVal.intValue()
    }
    text = "grid size: " +  sizeSlider.value.toInt
    textAlignment = TextAlignment.CENTER
    alignment = Pos.CENTER
    prefWidth = 130
    layoutX = 285
    layoutY = 270
  }

  val startButton = new Button {
    text = "start game"
    onAction = handle {
      if(p1TextField.text.length().intValue() == 0){
        p1TextField.text = "Player 1"
      }
      if(p2TextField.text.length().intValue() == 0){
        p2TextField.text = "Player 2"
      }
      gameController.createPlayer(0, p1TextField.text.value)
      gameController.createPlayer(1, p2TextField.text.value)
      gameController.createTable(sizeSlider.value.toInt)
      gameController.start()
    }
    layoutX = 312
    layoutY = 350
  }

  children ++= Seq(startButton, welcomeLabel, p1TextField, p2TextField, sizeSlider, descriptionLabel, gridSizeLabel)


}
