package view

import javafx.scene.paint.Color

import scalafx.scene.paint.PhongMaterial
import scalafx.scene.shape.Box
import scalafx.scene.{Node, Group, Scene}

class GameScene(root: Group) extends Scene(root) with Perspective {
  camera = cam
  val world = new EnhancedGroup()
  root.children addAll(camGroups._1, world)
  world.children add new Box() {
    width = 200
    height = 200
    depth = 2000
    material = new PhongMaterial(Color.LIGHTGREEN)
  }.asInstanceOf[Node]
}
