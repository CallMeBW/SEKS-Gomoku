package main

import control.GameController
import view.{SetupPane, StatusPane}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{AnchorPane, StackPane}
import scalafx.scene.paint.Color

object GomokuApp extends JFXApp {
  val WELCOME = "Welcome to Gomoku!"
  val WELCOME_MESSAGE = "Gomoku is a game for two players. The players alternately put their symbol on th board. The player who has 5 of his symbols in a row wins this gomoku."
  val HINT_P1 = "Player 1"
  val HINT_P2 = "Player 2"

  val controller = new GameController
  val setupPane = new SetupPane(controller){
    prefWidth = 700
    prefHeight = 440
  }
  val statusPane = new StatusPane {
    prefWidth = 700
    prefHeight = 60
    layoutY = 440
  }

  stage = new PrimaryStage{
    title.value = "Gomoku"
    width = 700
    height = 500
    resizable = false
    scene = new Scene {
      content = new AnchorPane{
        children add setupPane
        children add statusPane
      }
    }
  }
  start()

  def start() = {
    controller.setGomokuApp(this)
//    controller.createPlayers
    stage.show()
  }


}