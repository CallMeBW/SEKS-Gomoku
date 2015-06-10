package control

import model.Player

import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout.AnchorPane
import scalafx.stage.Stage

class GameController {
  //val table = new Table(10)
  val players = new Array[Player](2)

  def createPlayers() = {
    for (a <- 1 to symbols.length) {
      val dialogStage = new Stage {
        outer =>
        title = "Gomoku"
        scene = new Scene {
          root = new AnchorPane {
            prefHeight = 150
            prefWidth = 300
            var labelText = new Label{
              text = "Welcome to Gomoku\nPlease enter your name player " + a
            }
            AnchorPane.setTopAnchor(labelText, 5)
            AnchorPane.setLeftAnchor(labelText, 10)

            val input = new TextField{

            }
            AnchorPane.setTopAnchor(input, 50)
            AnchorPane.setLeftAnchor(input, 10)
            AnchorPane.setRightAnchor(input, 10)

            val submit = new Button{
              text = "Submit"
              onAction = handle{
                if(input.text.value.trim.isEmpty){
                  players(a - 1) = new Player("Player " + a, symbols(a - 1))
                } else {
                  players(a - 1) = new Player(input.text.value, symbols(a - 1))
                }
                println(players(a - 1).name + " " + players(a - 1).icon)
                outer.close()
              }
            }

            AnchorPane.setBottomAnchor(submit, 5)
            AnchorPane.setRightAnchor(submit, 5)

            children.addAll(labelText, input, submit)
          }
        }
      }
      dialogStage.showAndWait()
    }
  }

  def getPlayers(): Array[Player] = players

}
