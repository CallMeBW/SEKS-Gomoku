package view

import scalafx.scene.PerspectiveCamera

trait Perspective {

  case class Perspective(zoom: Int, rX: Int, rY: Int, rZ: Int, tZ: Int)

  val wholeBoard = Perspective(-1500, 35, 0, 180, 0)
  /**
   * (xyRot,xyTrans,zRot)
   */
  val camGroups = (new EnhancedGroup(), new EnhancedGroup(), new EnhancedGroup())
  val cam = new PerspectiveCamera() {
    nearClip = cameraNearClip
    farClip = cameraFarClip
  }
  camGroups._1.children add camGroups._2
  camGroups._2.children add camGroups._3
  camGroups._3.children add cam
}
