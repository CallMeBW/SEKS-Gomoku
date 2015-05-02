package view

import javafx.scene.transform.{Rotate, Scale, Translate}

import scalafx.scene.{Group, Node}

class EnhancedGroup extends Group {
  /**
   * Translate properties and pivot translates. A translate is a shift in one direction by an amount of pixels
   */
  var t: Translate = null
  var p: Translate = null
  var ip: Translate = null
  /**
   * Rotate properties. An enhanced group can be rotated around the 3 axes.
   */
  var rx: Rotate = null
  var ry: Rotate = null
  var rz: Rotate = null
  /**
   * The scale property stores the scale factor of this enhanced group.
   */
  private var s: Scale = null


  /**
   * Constructor to add all children to the enhanced group.
   *
   * @param childNodes all children used for this enhanced group.
   */
  def this(childNodes: Node) {
    this()
    children.addAll(childNodes)
  }


  /**
   * Assigns field variables to new objects.
   */
  private def assignVariables() {
    t = new Translate
    p = new Translate
    ip = new Translate
    s = new Scale
    ry = new Rotate
    rx = new Rotate
    rz = new Rotate
    ry.setAxis(Rotate.Y_AXIS)
    rx.setAxis(Rotate.X_AXIS)
    rz.setAxis(Rotate.Z_AXIS)
  }

  /**
   * Sets the translation of this group on the axes.
   *
   * @param x x translation in degrees
   * @param y y translation in degrees
   * @param z z translation in degrees
   */
  def setTranslate(x: Double, y: Double, z: Double) {
    t.setX(x)
    t.setY(y)
    t.setZ(z)
  }


  /**
   * Sets the rotation in degrees around the  axes.
   *
   * @param x x rotation in degrees
   * @param y y rotation in degrees
   * @param z z rotation in degrees
   */
  def setRotate(x: Double, y: Double, z: Double) {
    rx.setAngle(x)
    ry.setAngle(y)
    rz.setAngle(z)
  }

  /**
   * Sets the scale to an equal value for all directions (x, y and z).
   *
   * @param scaleFactor the factor by which all directions of this group are being scaled.
   */
  def setScale(scaleFactor: Double) {
    s.setX(scaleFactor)
    s.setY(scaleFactor)
    s.setZ(scaleFactor)
  }

  /**
   * Sets the scale of the x property.
   *
   * @param x x scale.
   */
  def setSx(x: Double) {
    s.setX(x)
  }

  /**
   * Sets the scale of the y property.
   *
   * @param y y scale
   */
  def setSy(y: Double) {
    s.setY(y)
  }

  /**
   * Sets the scale of the z property.
   *
   * @param z z scale
   */
  def setSz(z: Double) {
    s.setZ(z)
  }


  override def toString(): String = {
    getClass.getName + "{" + "rx=" + rx + ", ry=" + ry + ", rz=" + rz + ", s=" + s + ", t=" + t + '}'
  }

}
