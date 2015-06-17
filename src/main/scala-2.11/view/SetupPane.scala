package view


import java.awt.Toolkit
import javafx.beans.{value, InvalidationListener}
import javafx.beans.value.ObservableStringValue
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javax.swing.event.ChangeListener

import control.GameController
import main.GomokuApp

import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.scene.control._
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


  val checkbox = new CheckBox {
    selected = false
    text = "Play against computer"
    layoutX = 380
    layoutY = 160
  }

  val combobox = new ChoiceBox[String](){

    layoutX = 400
    layoutY = 190
    visible.bind(checkbox.selected)
    delegate.getItems.add("EASY")
    delegate.getItems.add("MEDIUM")
    delegate.getItems.add("HARD")
    delegate.getSelectionModel.select(0)
  }

  val p1TextField = new TextField {
    promptText = GomokuApp.HINT_P1
    layoutX = 170
    layoutY = 190
    prefWidth = 110
  }
  val p1SymField = new TextField {
    alignment = Pos.CENTER
    promptText = "\u2715"
    layoutX = 282
    layoutY = 190
    prefWidth = 35
  }
  val p2TextField = new TextField {
    promptText = GomokuApp.HINT_P2
    layoutX = 380
    layoutY = 190
    prefWidth = 110
    visible.bind(checkbox.selected.not())
  }
  val p2SymField = new TextField {
    alignment = Pos.CENTER
    promptText = "\u25CB"
    layoutX = 492
    layoutY = 190
    prefWidth = 35
  }

  val sizeSlider = new Slider {
    majorTickUnit = 1
    minorTickCount = 1
    min = 5
    max = 20
    snapToTicks = true
    value = 12
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
      gameController.createPlayer(0, p1TextField.text.value,p1SymField.text.value)
      if(checkbox.selected.value){
        gameController.createComputer(1,combobox.delegate.getSelectionModel.getSelectedItem match {
          case "EASY" => model.Mode.EASY
          case "MEDIUM" => model.Mode.MEDIUM
          case "HARD" => model.Mode.HARD
        },p2SymField.text.value)
      } else{
        gameController.createPlayer(1, p2TextField.text.value,p2SymField.text.value)
      }
      gameController.createTable(sizeSlider.value.toInt)
      gameController.start()
    }
    layoutX = 312
    layoutY = 350
    stylesheets add "style.css"
  }

  p1SymField.text.onInvalidate{
    if(p1SymField.text.value.length > 1){
      Toolkit.getDefaultToolkit.beep()
      p1SymField.text = p1SymField.text.value.substring(0,1)
    }
    if(p1SymField.text.value.equals(p2SymField.text.value) && !p1SymField.text.value.equals("")){
      p2SymField.text = ""
    }
  }
  p2SymField.text.onInvalidate{
    if(p2SymField.text.value.length > 1){
      Toolkit.getDefaultToolkit.beep()
      p2SymField.text = p2SymField.text.value.substring(0,1)
    }
    if(p2SymField.text.value.equals(p1SymField.text.value) &&  !p2SymField.text.value.equals("")){
      p1SymField.text = ""
    }
  }

  children ++= Seq(startButton, welcomeLabel, p1TextField, p2TextField,
    sizeSlider, descriptionLabel, gridSizeLabel,checkbox,combobox,p2SymField,p1SymField)


}
