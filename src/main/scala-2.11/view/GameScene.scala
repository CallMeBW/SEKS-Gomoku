package view


import scalafx.scene._
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.shape.Box

class GameScene(root: Group)
  extends Scene(root, gomokuWidth, gomokuHeight, true, SceneAntialiasing.Balanced)
  with Perspective {
  val world: javafx.scene.Group = new EnhGroup()
  root.children.add(world)
  root.children.add(camGroups._1)
  camera = cam
  buildNodes()


  private def buildNodes() {
    val redMaterial = new PhongMaterial {
      diffuseColor = Color.DARKRED
      specularColor = Color.RED
    }
    val zAxis = new Box(200, 150, 90) {
      material = redMaterial
    }
    world.getChildren.add(zAxis)
  }

}


