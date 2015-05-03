import java.awt.Toolkit

package object view {
  private val screenSize = Toolkit.getDefaultToolkit.getScreenSize
  private val screenSizeFactor = (0.9, 0.8)
  val gomokuTitle = "Gomoku"
  val gomokuWidth = screenSize.width * screenSizeFactor._1
  val gomokuHeight = screenSize.height * screenSizeFactor._2
  val cameraNearClip = 2
  val cameraFarClip = 10000
}
