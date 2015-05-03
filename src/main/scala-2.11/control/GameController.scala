package control

import model.Player

class GameController {
  def getPlayers: (Player, Player) =
    (new Player("Bernd", symbols(1)), new Player("Andrea", symbols(1)))

}
