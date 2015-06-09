package control

class Table(val n: Int) {
  require(n >= 5, "Table needs atleast a size of 5")

  val size = n
  create()

  def create() = ???

  def setEntry(x: Int, y: Int, s: String):Boolean = ???

  def getEntry(x:Int, y:Int):String = ???

  def winTest(x:Int, y:Int, s:String):Boolean = ???
}
