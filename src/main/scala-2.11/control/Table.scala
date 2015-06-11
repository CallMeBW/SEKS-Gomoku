package control

import scalafx.beans.property.StringProperty

class Table(val size: Int) {
  require(size >= 5, "Table needs atleast a size of 5")

  val table = Array.fill[StringProperty](size, size) {
    new StringProperty("")
  }

  def setEntry(x: Int, y: Int, s: String): Boolean = {
    if (x < 0 || y < 0 || x >= size || y >= size) {
      return false
    }
    if (!table(x)(y).value.equals("")) {
      return false
    }
    table(x)(y).set(s)
    true
  }

  def getEntry(x: Int, y: Int): StringProperty = {
    table(x)(y)
  }

  def winTest(x: Int, y: Int, s: String): Boolean =
      check(x, y, s, 0) + check(x, y, s, 4) - 1 == 5 ||
      check(x, y, s, 1) + check(x, y, s, 5) - 1 == 5 ||
      check(x, y, s, 2) + check(x, y, s, 6) - 1 == 5 ||
      check(x, y, s, 3) + check(x, y, s, 7) - 1 == 5


  /**
   * Kaz-Mayer-Algorithm
   */
  private def check(x: Int, y: Int, s: String, dir: Int): Int = {
    if (x < 0 || y < 0 || x >= size || y >= size) {
      return 0
    }
    if (!table(x)(y).value.equals(s)) {
      return 0
    }
    dir match {
      case 0 => 1 + check(x, y + 1, s, dir)
      case 1 => 1 + check(x + 1, y + 1, s, dir)
      case 2 => 1 + check(x + 1, y, s, dir)
      case 3 => 1 + check(x + 1, y - 1, s, dir)
      case 4 => 1 + check(x, y - 1, s, dir)
      case 5 => 1 + check(x - 1, y - 1, s, dir)
      case 6 => 1 + check(x - 1, y, s, dir)
      case 7 => 1 + check(x - 1, y + 1, s, dir)
    }
  }
}
