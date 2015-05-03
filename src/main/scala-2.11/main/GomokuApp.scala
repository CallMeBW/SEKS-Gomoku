package main

import _root_.control.GameController
import view.{GameScene, GomokuAppStage}

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.input.{KeyEvent, MouseEvent, ScrollEvent}

object GomokuApp extends JFXApp {
  val controller = new GameController()
  val gameScene = new GameScene(new Group())
  stage = new GomokuAppStage(gameScene){
    gameScene.onMousePressed = (me: MouseEvent) => gameScene.onPress(me)
    gameScene.onMouseDragged = (me: MouseEvent) => gameScene.onDrag(me)
    gameScene.onScroll = (se: ScrollEvent) => gameScene.onScroll(se)
    gameScene.onKeyPressed = (ke: KeyEvent) => gameScene.onKey(ke)
  }
  start()

  def start(): Unit ={
    controller.getPlayers
    // ...
  }



}