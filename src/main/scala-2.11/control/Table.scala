package control

import model.Mode
import model.Mode.Mode

import scala.util.Random
import scalafx.beans.property.StringProperty

class Table(val size: Int) {
  require(size >= 5, "Table needs atleast a size of 5")

  val table = Array.fill[StringProperty](size, size) {
    new StringProperty("-")
  }

  def setEntry(x: Int, y: Int, s: String): Boolean = {
    if (x < 0 || y < 0 || x >= size || y >= size) {
      return false
    }
    if (!table(x)(y).value.equals("-")) {
      return false
    }
    table(x)(y).set(s)
    true
  }

  def getEntry(x: Int, y: Int): StringProperty = {
    table(x)(y)
  }

  def calculateNewEntry(lastX: Int, lastY: Int, playerIcon: String, mode: Mode): (Int, Int) = mode match {
    case Mode.EASY => {
      calculateNewEasyEntry(lastX, lastY, playerIcon)
    }
    case Mode.MEDIUM => {
      calculateNewMediumEntry(lastX, lastY, playerIcon)
    }
    case Mode.HARD => {
      calculateNewHardEntry(lastX, lastY, playerIcon)
    }
  }

  private def calculateNewEasyEntry(lastX: Int, lastY: Int, playerIcon: String): (Int, Int) = {
    def getRandomField(): (Int, Int) = {
      var randomNumber1 = Random.nextInt(size-1)
      var randomNumber2 = Random.nextInt(size-1)

      if (table(randomNumber1)(randomNumber2).value.equals("-")) {
        (randomNumber1, randomNumber2)
      } else {
        for (a <- randomNumber1 to size - 1) {
          randomNumber1 = randomNumber1 + 1
          for (b <- randomNumber2 to size - 1) {
            if (table(randomNumber1)(randomNumber2).value.equals("-")) {
              (randomNumber1, randomNumber2)
            } else {
              randomNumber2 = randomNumber2 + 1
              if (table(randomNumber1)(randomNumber2).value.equals("-")) {
                (randomNumber1, randomNumber2)
              }
            }
          }
        }
        (-1, -1)
      }
    }
    getRandomField()
  }

  private def calculateNewMediumEntry(lastX: Int, lastY: Int, playerIcon: String): (Int, Int) = {
    if (check(lastX, lastY, playerIcon, 0) + check(lastX, lastY, playerIcon, 4) - 1 == 4) {

      (-1, -1)
    } else if (check(lastX, lastY, playerIcon, 1) + check(lastX, lastY, playerIcon, 5) - 1 == 4) {

      (-1, -1)
    } else if (check(lastX, lastY, playerIcon, 2) + check(lastX, lastY, playerIcon, 6) - 1 == 4) {

      (-1, -1)
    } else if (check(lastX, lastY, playerIcon, 3) + check(lastX, lastY, playerIcon, 7) - 1 == 4) {

      (-1, -1)
    } else {
      calculateNewEasyEntry(lastX, lastY, playerIcon)
    }
  }

  private def calculateNewHardEntry(lastX: Int, lastY: Int, playerIcon: String): (Int, Int) = {
    if (check(lastX, lastY, playerIcon, 0) + check(lastX, lastY, playerIcon, 4) - 1 == 3) {

      (-1, -1)
    } else if (check(lastX, lastY, playerIcon, 1) + check(lastX, lastY, playerIcon, 5) - 1 == 3) {

      (-1, -1)
    } else if (check(lastX, lastY, playerIcon, 2) + check(lastX, lastY, playerIcon, 6) - 1 == 3) {

      (-1, -1)
    } else if (check(lastX, lastY, playerIcon, 3) + check(lastX, lastY, playerIcon, 7) - 1 == 3) {

      (-1, -1)
    } else {
      calculateNewEasyEntry(lastX, lastY, playerIcon)
    }
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
