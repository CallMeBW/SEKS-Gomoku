package main

import control.GameController

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

object GomokuApp extends JFXApp {
  stage = new PrimaryStage{
    title.value = "Gomoku"
    width = 500
    height = 500
    scene = new Scene {

    }
  }
  val controller = new GameController
  start()

  def start() = {
    controller.createPlayers
    ???
  }


}