package main


import javafx.collections.ObservableList
import javafx.scene

import control.GameController
import view.{Board, SetupPane, StatusPane}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{AnchorPane, Pane}

object GomokuApp extends JFXApp {
  val WELCOME = "Welcome to Gomoku!"
  val WELCOME_STATUS = "Set up your game!"
  val WELCOME_MESSAGE = "Gomoku is a game for two players. The players alternately put their symbol on the board. The player who has 5 of his symbols in a row wins Gomoku."
  val HINT_P1 = "Player 1"
  val HINT_P2 = "Player 2"
  private var mainChildren: ObservableList[scene.Node] = null

  val controller = new GameController
  val setupPane = new SetupPane(controller) {
    prefHeight = 440
  }
  val boardPane = new Board(controller) {
    prefWidth = 700
    prefHeight = 440
  }
  val statusPane = new StatusPane {
    prefHeight = 60
  }

  stage = new PrimaryStage {
    title.value = "Gomoku"
    width = 700
    height = 500
    resizable = false
    scene = new Scene {
      mainChildren = content
      content = new AnchorPane {
        children add setupPane
        children add statusPane
      }
    }
  }
  start()
  statusPane.prefWidth bind stage.width
  stage.height.onInvalidate { op: scalafx.beans.Observable =>
    statusPane.layoutY.set(stage.height.value - 90)
    boardPane.prefHeight = stage.height.value - 90
  }
  stage.width.onInvalidate { op: scalafx.beans.Observable =>

  }

  statusPane.layoutY = stage.height.value - 90
  boardPane.prefHeight = stage.height.value - 90
  boardPane.prefWidth bind stage.width

  def setMainPane(panel: Pane): Unit = {
    stage.resizable = panel.equals(boardPane)
    if (panel.equals(setupPane)) {
      stage.width = 700
      stage.height = 500
    }
    mainChildren.clear()
    mainChildren.add(statusPane)
    mainChildren.add(panel)
  }


  def start() = {
    statusPane.setStatus(WELCOME_STATUS)
    stage.show()
  }


}