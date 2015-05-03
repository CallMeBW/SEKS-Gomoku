package view

import scalafx.scene.PerspectiveCamera
import scalafx.scene.input.{MouseEvent, ScrollEvent}

trait Perspective {

  case class Setting(zoom: Int, rX: Int, rY: Int, rZ: Int, tZ: Int)

  val wholeBoard = Setting(-1500, 35, 0, 180, 0)
  /**
   * (xyRot,xyTrans,zRot)
   */
  val camGroups = (new EnhGroup(), new EnhGroup(), new EnhGroup())
  val cam = new PerspectiveCamera(fixedEyeAtCameraZero = true) {
    nearClip = cameraNearClip
    farClip = cameraFarClip
  }
  private final val cameraDistance: Double = 450
  camGroups._1.children.add(camGroups._2)
  camGroups._2.children.add(camGroups._3)
  camGroups._3.children.add(cam)
  camGroups._3.rotateZ = 180.0
  cam.nearClip = cameraNearClip
  cam.farClip = cameraFarClip
  cam.translateZ = -cameraDistance
  camGroups._1.ry.angle = 40
  camGroups._1.rx.angle = 40
  private var mousePosX: Double = .0
  private var mousePosY: Double = .0
  private var mouseOldX: Double = .0
  private var mouseOldY: Double = .0
  private var mouseDeltaX: Double = .0
  private var mouseDeltaY: Double = .0

  def onScroll(scrollEvent: ScrollEvent): Unit ={
    val z = cam.translateZ()
    val newZ = z + scrollEvent.deltaY
    cam.translateZ = newZ
  }

  def onPress(mouseEvent: MouseEvent): Unit ={
    mousePosX = mouseEvent.sceneX
    mousePosY = mouseEvent.sceneY
    mouseOldX = mouseEvent.sceneX
    mouseOldY = mouseEvent.sceneY
  }

  def onDrag(me: MouseEvent): Unit ={
    mouseOldX = mousePosX
    mouseOldY = mousePosY
    mousePosX = me.sceneX
    mousePosY = me.sceneY
    mouseDeltaX = mousePosX - mouseOldX
    mouseDeltaY = mousePosY - mouseOldY
    val modifier = if (me.isControlDown) 0.1 else if (me.isShiftDown) 10 else 1.0
    val modifierFactor = 0.1
    if (me.isPrimaryButtonDown) {
      camGroups._1.ry.angle = camGroups._1.ry.angle() - mouseDeltaX * modifierFactor * modifier * 2.0
      camGroups._1.rx.angle = camGroups._1.rx.angle() + mouseDeltaY * modifierFactor * modifier * 2.0
    } else if (me.isMiddleButtonDown) {
     camGroups._2.t.x = camGroups._2.t.x() + mouseDeltaX * modifierFactor * modifier * 0.3
     camGroups._2.t.x = camGroups._2.t.y() + mouseDeltaY * modifierFactor * modifier * 0.3
    }
  }
}
