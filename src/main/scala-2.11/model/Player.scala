package model

import control.Table

class Player(val name: String, val icon: Char) {
    require(name != null, "Players need a name")
    require(icon != null, "Players need a icon")

    def placeSymbolOnTable(table:Table) = ???
}
