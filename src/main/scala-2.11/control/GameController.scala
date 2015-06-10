package control

import model.Player

class GameController {
  def getPlayers: (Player, Player) = {
    val (p1,p2) = (new Player("Bernd", symbols(1)), new Player("Andrea", symbols(1)))
    println(p1.icon)
    (p1,p2)
  }


}
