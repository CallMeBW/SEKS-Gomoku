package scalafxsample

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/**
 * TODO Alle: nachlesen http://www.scalafx.org/docs/home/
 */
object HoverSample extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title.value = "Hello Stage"
    width = 600
    height = 450
    scene = new Scene {
      fill = Color.LIGHTGREEN
      content = new Rectangle {
        x = 25
        y = 40
        width = 100
        height = 100
        fill <== when(hover) choose Color.GREEN otherwise Color.RED
      }
    }
  }
}