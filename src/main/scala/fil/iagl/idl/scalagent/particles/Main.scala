package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.Observer
import fil.iagl.idl.scalagent.particles.Test._

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

object Main extends JFXApp with Observer {

  Main.main(Array(""))
  val commandWithHandling = new ParticlesMainCommand()
  commandWithHandling.handleCommand(args)

  stage = new JFXApp.PrimaryStage {
    title.value = "Hello Stage"
    width = 600
    height = 450
    scene = new Scene {
      fill = Color.LightGreen
      content = new Rectangle {
        x = 25
        y = 40
        width = 100
        height = 100
        fill <== when (hover) choose Color.Green otherwise Color.Red
      }
    }
  }

  override def update(): Unit = println("update()")
}
