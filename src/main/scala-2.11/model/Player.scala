package model

import control.Table

class Player(val name: String, val icon: Char) {
  require(name != null, "Players need a name")

    val WON = s"$name won the game."
    val ROUND = s"It's $name's turn."

  def placeSymbolOnTable(table: Table, x: Int, y: Int): Boolean = table.setEntry(x, y, icon)
}
