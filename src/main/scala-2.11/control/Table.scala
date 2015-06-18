package control

import main.GomokuApp
import model.Mode.Mode
import model.{Computer, Mode, Player}

import scala.annotation.tailrec
import scala.util.Random
import scalafx.beans.property.StringProperty

class Table(val size: Int) {
  require(size >= 5, "Table needs atleast a size of 5")

  var placedCounter = 0

  val table = Array.fill[StringProperty](size, size) {
    new StringProperty(GomokuApp.EMPTY_FIELD)
  }

  def setEntry(x: Int, y: Int, s: String): Boolean = {
    if (x < 0 || y < 0 || x >= size || y >= size) {
      return false
    }
    if (!table(x)(y).value.equals(GomokuApp.EMPTY_FIELD)) {
      return false
    }
    table(x)(y).set(s)
    placedCounter = placedCounter + 1
    true
  }

  def getEntry(x: Int, y: Int): StringProperty = {
    table(x)(y)
  }


  def calculateNewEntry(lastX: Int, lastY: Int, playerIcon: String, mode: Mode, player: Player): (Int, Int) =
    mode match {
      case Mode.EASY =>
        calculateNewEasyEntry(-1, -1, playerIcon, 0)
      case Mode.MEDIUM =>
        calculateNewMediumEntry(lastX, lastY, playerIcon, player)
      case Mode.HARD =>
        calculateNewHardEntry(lastX, lastY, playerIcon, player)
    }

  @tailrec private def calculateNewEasyEntry(lastX: Int, lastY: Int, playerIcon: String, dir: Int): (Int, Int) = {
    var x: Int = 0
    var y: Int = 0
    if (lastX >= size || lastY >= size || lastX < 0 || lastY < 0 || lastX == -1 && lastY == -1) {
      x = Random.nextInt(size - 1)
      y = Random.nextInt(size - 1)
    } else {
      x = lastX
      y = lastY
    }
    if (table(x)(y).value.equals(GomokuApp.EMPTY_FIELD)) {
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

  private def calculateNewMediumEntry(lastX: Int, lastY: Int, playerIcon: String, player: Player): (Int,
    Int) = {
    if ((check(lastX, lastY, playerIcon, 0, 0) + check(lastX, lastY, playerIcon, 4, 0) - 1) == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 0, 4)
    } else if ((check(lastX, lastY, playerIcon, 1, 0) + check(lastX, lastY, playerIcon, 5, 0) - 1) == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 1, 5)
    } else if ((check(lastX, lastY, playerIcon, 2, 0) + check(lastX, lastY, playerIcon, 6, 0) - 1) == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 2, 6)
    } else if ((check(lastX, lastY, playerIcon, 3, 0) + check(lastX, lastY, playerIcon, 7, 0) - 1) == 4) {
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 3, 7)
    } else {
      if (!player.isInstanceOf[Computer]) {
        (-1, -1)
      } else {
        calculateNewEasyEntry(-1, -1, playerIcon, 0)
      }
    }
  }

  private def calculateNewHardEntry(lastX: Int, lastY: Int, playerIcon: String, player: Player): (Int, Int) = {
    val NS = check(lastX, lastY, playerIcon, 0, 0) + check(lastX, lastY, playerIcon, 4, 0) - 1
    val WO = check(lastX, lastY, playerIcon, 2, 0) + check(lastX, lastY, playerIcon, 6, 0) - 1
    val NWSO = check(lastX, lastY, playerIcon, 1, 0) + check(lastX, lastY, playerIcon, 5, 0) - 1
    val NOSW = check(lastX, lastY, playerIcon, 3, 0) + check(lastX, lastY, playerIcon, 7, 0) - 1

    if(NS >= 3 && NS >= WO && NS >= NWSO && NS >= NOSW){
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 0, 4)
    } else if(WO >= 3 && WO >= NS && WO >= NWSO && WO >= NOSW){
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 2, 6)
    } else if(NWSO >= 3 && NWSO >= NS && NWSO >= WO && NWSO >= NOSW){
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 1, 5)
    } else if(NOSW >= 3 && NOSW >= NS && NOSW >= WO && NOSW >= NWSO){
      calculateNewDifferentEntry(lastX, lastY, playerIcon, 3, 7)
    }  else {
      if (!player.isInstanceOf[Computer]) {
        (-1, -1)
      } else {
        calculateNewEasyEntry(-1, -1, playerIcon, 0)
      }
    }
  }

  private def calculateNewDifferentEntry(x: Int, y: Int, s: String, dir1: Int, dir2: Int): (Int, Int) = {
    val tuple1 = nextDifferentEntry(x, y, s, dir1)
    if (tuple1._1 == -1 || tuple1._2 == -1) {
      val tuple2 = nextDifferentEntry(x, y, s, dir2)
      if (tuple2._1 == -1 || tuple2._2 == -1) {
        calculateNewEasyEntry(-1, -1, s, 0)
      } else {
        tuple2
      }
    } else {
      tuple1
    }
  }

  @tailrec private def nextDifferentEntry(x: Int, y: Int, s: String, dir: Int): (Int, Int) = {
    if (x < 0 || y < 0 || x >= size || y >= size) {
      (-1, -1)
    } else if (table(x)(y).value.equals(GomokuApp.EMPTY_FIELD)) {
      (x, y)
    } else if (table(x)(y).value.equals(s)) {
      dir match {
        case 0 => nextDifferentEntry(x, y + 1, s, dir)
        case 1 => nextDifferentEntry(x + 1, y + 1, s, dir)
        case 2 => nextDifferentEntry(x + 1, y, s, dir)
        case 3 => nextDifferentEntry(x + 1, y - 1, s, dir)
        case 4 => nextDifferentEntry(x, y - 1, s, dir)
        case 5 => nextDifferentEntry(x - 1, y - 1, s, dir)
        case 6 => nextDifferentEntry(x - 1, y, s, dir)
        case 7 => nextDifferentEntry(x - 1, y + 1, s, dir)
      }
    } else {
      (-1, -1)
    }
  }

  def checkForTie(): Boolean = placedCounter == size * size


  def winTest(x: Int, y: Int, s: String): Boolean =
    (check(x, y, s, 0,0) + check(x, y, s, 4, 0) - 1) == 5 ||      // vertical
      (check(x, y, s, 1,0) + check(x, y, s, 5, 0) - 1) == 5 ||    // diagonal
      (check(x, y, s, 2, 0) + check(x, y, s, 6, 0) - 1) == 5 ||   // horizontal
      (check(x, y, s, 3, 0) + check(x, y, s, 7, 0) - 1) == 5      // diagonal


  /**
   * Kaz-Mayer-Algorithm
   */
  @tailrec private def check(x: Int, y: Int, s: String, dir: Int, value:Int): Int = {
    if (x < 0 || y < 0 || x >= size || y >= size) {       // out of field
      return value
    }
    if (!table(x)(y).value.equals(s)) {                   // not equals
      value
    } else {                                              // equals
      dir match {
        case 0 => check(x, y + 1, s, dir, value + 1)      // N
        case 1 => check(x + 1, y + 1, s, dir, value + 1)  // NE
        case 2 => check(x + 1, y, s, dir, value + 1)      // E
        case 3 => check(x + 1, y - 1, s, dir, value + 1)  // SE
        case 4 => check(x, y - 1, s, dir, value + 1)      // S
        case 5 => check(x - 1, y - 1, s, dir, value + 1)  // SW
        case 6 => check(x - 1, y, s, dir, value + 1)      // W
        case 7 => check(x - 1, y + 1, s, dir, value + 1)  // NW
      }
    }
  }
}
