package model

import control.Table

class Player(val n: String, val i: Char) {
    require(n != null, "Players need a name")
    require(i != null, "Players need a icon")
    val name = n
    val icon = i

    def placeSymbolOnTable(table:Table) = ???
}
