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
    case Mode.EASY =>
      calculateNewEasyEntry(lastX, lastY, playerIcon, 0)
    case Mode.MEDIUM =>
      calculateNewMediumEntry(lastX, lastY, playerIcon)
    case Mode.HARD =>
      calculateNewHardEntry(lastX, lastY, playerIcon)
  }

  private def calculateNewEasyEntry(lastX: Int, lastY: Int, playerIcon: String, dir: Int): (Int, Int) = {
    var x: Int = null
    var y: Int = null
    if (lastX >= size || lastY >= size || lastX < 0 || lastY < 0 || lastX == -1 && lastY == -1) {
      x = Random.nextInt(size - 1)
      y = Random.nextInt(size - 1)
    } else {
      x = lastX;
      y = lastY;
    }
    if (table(x)(y).value.equals("-")) {
      (x, y)
    } else {
      dir match {
        case 0 => calculateNewEasyEntry(x, y + 1, playerIcon, 1)
        case 1 => calculateNewEasyEntry(x + 1, y + 1, playerIcon, 2)
        case 2 => calculateNewEasyEntry(x + 1, y, playerIcon, 3)
        case 3 => calculateNewEasyEntry(x + 1, y - 1, playerIcon, 4)
        case 4 => calculateNewEasyEntry(x, y - 1, playerIcon, 5)
        case 5 => calculateNewEasyEntry(x - 1, y - 1, playerIcon, 6)
        case 6 => calculateNewEasyEntry(x - 1, y, playerIcon, 7)
        case 7 => calculateNewEasyEntry(x - 1, y + 1, playerIcon, 8)
        case 8 => calculateNewEasyEntry(-1, -1, playerIcon, 0)
      }
      }
    }

  private def calculateNewMediumEntry(lastX: Int, lastY: Int, playerIcon: String): (Int, Int) = {
    if (check(lastX, lastY, playerIcon, 0) + check(lastX, lastY, playerIcon, 4) - 1 == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 0, 4)
    } else if (check(lastX, lastY, playerIcon, 1) + check(lastX, lastY, playerIcon, 5) - 1 == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 1, 5)
    } else if (check(lastX, lastY, playerIcon, 2) + check(lastX, lastY, playerIcon, 6) - 1 == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 2, 6)
    } else if (check(lastX, lastY, playerIcon, 3) + check(lastX, lastY, playerIcon, 7) - 1 == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 3, 7)
    } else {
      calculateNewEasyEntry(lastX, lastY, playerIcon, 0)
    }
  }

  private def calculateNewHardEntry(lastX: Int, lastY: Int, playerIcon: String): (Int, Int) = {
    if (check(lastX, lastY, playerIcon, 0) + check(lastX, lastY, playerIcon, 4) - 1 == 3) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 0, 4)
    } else if (check(lastX, lastY, playerIcon, 1) + check(lastX, lastY, playerIcon, 5) - 1 == 3) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 1, 5)
    } else if (check(lastX, lastY, playerIcon, 2) + check(lastX, lastY, playerIcon, 6) - 1 == 3) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 2, 6)
    } else if (check(lastX, lastY, playerIcon, 3) + check(lastX, lastY, playerIcon, 7) - 1 == 3) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 3, 7)
    } else {
      calculateNewEasyEntry(lastX, lastY, playerIcon, 0)
    }
  }

  private def calculateNewDifferentEntry(x: Int, y: Int, s: String, dir1: Int, dir2: Int): (Int, Int) = {
    val tuple1 = nextDifferentEntry(x, y, s, dir1)
    if (tuple1._1 == -1 || tuple1._2 == -1) {
      val tuple2 = nextDifferentEntry(x, y, s, dir2)
      if (tuple2._1 == -1 || tuple2._2 == -1) {
        calculateNewEasyEntry(x, y, s, 0)
      } else {
        tuple2
      }
    } else {
      tuple1
    }
  }

  private def nextDifferentEntry(x: Int, y: Int, s: String, dir: Int): (Int, Int) = {
    if (x < 0 || y < 0 || x >= size || y >= size) {
      return (-1, -1)
    }
    table(x)(y).value match {
      case "-" => (x, y)
      case s => dir match {
        case 0 => nextDifferentEntry(x, y + 1, s, dir)
        case 1 => nextDifferentEntry(x + 1, y + 1, s, dir)
        case 2 => nextDifferentEntry(x + 1, y, s, dir)
        case 3 => nextDifferentEntry(x + 1, y - 1, s, dir)
        case 4 => nextDifferentEntry(x, y - 1, s, dir)
        case 5 => nextDifferentEntry(x - 1, y - 1, s, dir)
        case 6 => nextDifferentEntry(x - 1, y, s, dir)
        case 7 => nextDifferentEntry(x - 1, y + 1, s, dir)
      }
      case d: String => (-1, -1)
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
