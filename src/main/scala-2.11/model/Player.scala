package model

import control.Table

class Player(val name: String, val icon: String) {
  require(name != null, "Players need a name")

  val WON = s"$name wins the game."
  val ROUND = s"It's $name's turn."

  var lastX: Int = -1
  var lastY: Int = -1

  def placeSymbolOnTable(table: Table, x: Int, y: Int): Boolean = {
    lastX = x
    lastY = y
    table.setEntry(x, y, icon)
  }
}
