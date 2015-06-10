package main

import control.GameController
import view.{SetupPane, StatusPane}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{AnchorPane, StackPane}

object GomokuApp extends JFXApp {
  val WELCOME = "Welcome to Gomoku!"
  val WELCOME_MESSAGE = "Gomoku is a game for two suckers that are bored. FUCK YOU!"

  val controller = new GameController

  var setupPane:SetupPane = null
  var statusPane:StatusPane = null

  stage = new PrimaryStage{
    title.value = "Gomoku"
    width = 500
    height = 500
    val stackPane = new StackPane()
    scene = new Scene {
      content = new AnchorPane{
        setupPane = new SetupPane(controller)
        statusPane = new StatusPane()
        stackPane.children.add(setupPane)
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