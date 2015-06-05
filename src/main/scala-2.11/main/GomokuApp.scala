package main

import _root_.control.GameController

import scalafx.application.JFXApp

object GomokuApp extends JFXApp {
  val controller = new GameController()
  start()

  def start(): Unit = {
    controller.getPlayers
    // ...
  }


}