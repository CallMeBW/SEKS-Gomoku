package view

import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Group

class GomokuAppStage extends PrimaryStage {
  width = gomokuWidth
  height = gomokuHeight
  title = gomokuTitle
  scene = new GameScene(new Group())
}
