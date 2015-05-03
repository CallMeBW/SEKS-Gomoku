package view

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.input.{KeyEvent, MouseEvent, ScrollEvent}

object MoleculeSampleDemo extends JFXApp {
  app =>

  private final val root = new Group()


  stage = new JFXApp.PrimaryStage {
    val gs = new GameScene(root) {
      title = gomokuTitle
    }
    scene = gs
    handleKeyboard(gs)
    handleMouse(gs)
  }


  private def handleMouse(scene: GameScene) {
    scene.onMousePressed = (me: MouseEvent) => {
      scene.onPress(me)
    }
    scene.onMouseDragged = (me: MouseEvent) => {
      scene.onDrag(me)
    }
    scene.onScroll = (se: ScrollEvent) => {
      scene.onScroll(se)
    }
  }

  private def handleKeyboard(scene: GameScene) {
    scene.onKeyPressed = (event: KeyEvent) => {
      event.getCode match {
        case _ =>
      }
    }
  }
}