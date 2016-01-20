package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.Observer

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

 class View extends JFXApp with Observer {

  var mainContainer = new GridPane()
  var visibility = false
  var command = new ParticlesMainCommand()

  def test(): Unit = {
    stage = new PrimaryStage {
      title.value = "Particles"
      mainContainer.prefWidth = command.envSize * command.agentSize
      mainContainer.prefHeight = command.envSize * command.agentSize
      scene = new Scene {
        root = mainContainer
        val cons1 = new RowConstraints()
        cons1.vgrow = Priority.Never

        val cons2 = new RowConstraints()
        cons2.vgrow = Priority.Always

        mainContainer.rowConstraints.addAll(cons1, cons2)
      }
    }
  }



  override def update(takenCells: Array[Array[Boolean]]): Unit = {
    println("mainContainer : " + mainContainer)
    mainContainer.children.clear()
    for (i <- takenCells.indices) {
      for (j <- takenCells.indices) {
        val particle = new Rectangle {
          width = command.agentSize
          height = command.agentSize
          x = i
          y = j
          fill = if (takenCells(i)(j)) Color.Red else Color.White
        }
        mainContainer.add(particle, i, j)
      }
    }
  }

}

