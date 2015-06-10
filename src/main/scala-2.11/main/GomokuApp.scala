package main

import javafx.fxml.FXMLLoader

import _root_.control.GameController
import model.Board

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.stage.Stage

object GomokuApp extends JFXApp {
  val controller = new GameController()
  stage = new PrimaryStage{
    scene = new Scene{
      val loader = new FXMLLoader(this.getClass.getResource(""))
    }
  }
  start()

  def start(): Unit = {
    controller.getPlayers
    // ...
  }


}