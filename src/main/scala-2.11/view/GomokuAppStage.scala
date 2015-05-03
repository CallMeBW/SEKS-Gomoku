package view

import scalafx.application.JFXApp.PrimaryStage

class GomokuAppStage(val gameScene: GameScene) extends PrimaryStage {
  scene = gameScene
  width = gomokuWidth
  height = gomokuHeight
  title = gomokuTitle
}
