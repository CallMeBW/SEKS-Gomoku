package control

import view.GomokuAppStage

import scalafx.application.JFXApp

object GomokuApp extends JFXApp {
  val controller = new GameController
  stage = new GomokuAppStage
}
