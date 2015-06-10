package main

import control.GameController
import view.{SetupPane, StatusPane, Board}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{StackPane, AnchorPane}

object GomokuApp extends JFXApp {
  stage = new PrimaryStage{
    title.value = "Gomoku"
    width = 500
    height = 500
    val stackPane = new StackPane()
    scene = new Scene {
      content = new AnchorPane{
        val boardPane = new Board()
        val setupPane = new SetupPane()
        val statusPane = new StatusPane()
        stackPane.children.add(boardPane)
        stackPane.children.add(setupPane)
      }
    }
  }
  val controller = new GameController
  start()

  def start() = {
//    controller.createPlayers
    stage.show()
  }


}