package view

import scalafx.Includes._
import scalafx.geometry.Point3D
import scalafx.scene.Scene
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.shape.Box

class GameScene extends Scene {
  fill = Color.LIGHTGREEN
  content = new Box() {
    rotationAxis.value = new Point3D(1, 1, 1)
    rotate = 40
    translateX = 300
    translateY = 200
    width = 100
    height = 100
    depth = 80
    material <== when(hover) choose new PhongMaterial(Color.RED) otherwise new PhongMaterial(Color.GREEN)
  }
}
