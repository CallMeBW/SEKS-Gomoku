package view

import main.GomokuApp._

import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.AnchorPane
import scalafx.scene.text.{Font, TextAlignment}

class StatusPane extends AnchorPane { outer =>
  stylesheets add "style.css"
  id = "statusPane"
  val statusLabel = new Label() {
    id = "statusLabel"
    textAlignment = TextAlignment.CENTER
    alignment = scalafx.geometry.Pos.CENTER
    text = "Hallo Welt"
    font = new Font(17)
    padding = Insets(0,50,0,50)
  }
  prefWidth.onInvalidate { op: scalafx.beans.Observable =>
    statusLabel.prefWidth = prefWidth.value
    println(width.value)
  }
  children add statusLabel

  def setStatus(text: String): Unit = {
    statusLabel.text = text
  }
}
