package model

import control.Table

class Player(val name: String, val icon: Char) {
    require(name != null, "Players need a name")
    require(icon != null, "Players need a icon")

    val WON = s"$name won the game."
    val ROUND = s"It's $name's turn."

    def placeSymbolOnTable(table:Table) = ???
}
