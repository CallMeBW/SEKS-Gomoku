package view

import scalafx.scene.control.Label
import scalafx.scene.layout.AnchorPane
import scalafx.scene.text.{Font, TextAlignment}

class StatusPane extends AnchorPane {
  style = "-fx-background-color: lightgray;"
  val statusLabel = new Label() {
    textAlignment = TextAlignment.CENTER
    alignment = scalafx.geometry.Pos.CENTER
    style = "-fx-text-fill: #006464;"
    text = "Hallo Welt"
    font = new Font(17)
    prefWidth = 600
    prefHeight = 30
    layoutX = 50
  }
  children add statusLabel

  def setStatus(text: String): Unit = {
    statusLabel.text = text
  }
}
