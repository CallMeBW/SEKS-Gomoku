package view

import scalafx.scene.control.Label
import scalafx.scene.layout.AnchorPane
import scalafx.scene.text.{Font, TextAlignment}

class StatusPane extends AnchorPane {
  stylesheets add "style.css"
  id = "statusPane"
  val statusLabel = new Label() {
    id = "statusLabel"
    textAlignment = TextAlignment.CENTER
    alignment = scalafx.geometry.Pos.CENTER
    text = "Hallo Welt"
    font = new Font(17)
    prefWidth = 600
    prefHeight = 50
    layoutX = 50
  }
  children add statusLabel

  def setStatus(text: String): Unit = {
    statusLabel.text = text
  }
}
