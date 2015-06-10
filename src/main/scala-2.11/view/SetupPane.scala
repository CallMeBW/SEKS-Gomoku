package view


import main.GomokuApp

import scalafx.scene.control.{Slider, Label, Button, TextField}
import scalafx.scene.layout.AnchorPane

class SetupPane extends AnchorPane {
  prefHeight = 250
  prefWidth = 300

  val welcomeLabel = new Label{
    text = GomokuApp.WELCOME
  }
  val playerNameOne = new TextField
  val playerNameTwo = new TextField
  val sizeSlider = new Slider{
    majorTickUnit = 1
    min = 5
    max = 20
  }

  val submit = new Button{
    text = "Submit"
  }

}
