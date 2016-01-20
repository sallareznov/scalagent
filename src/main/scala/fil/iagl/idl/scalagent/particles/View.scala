package fil.iagl.idl.scalagent.particles

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

import fil.iagl.idl.scalagent.base.Observer

import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class View(model: Model) extends Application with Observer {

  model.addObserver(this)
  var mainContainer = new GridPane
  var visibility = false
  var command = new ParticlesMainCommand

  def this() {
    this(View.defaultModel())
  }

  override def start(stage: Stage): Unit = {
    println("START")
    stage.setTitle("Particles")
    mainContainer.prefWidth = command.envSize * command.agentSize
    mainContainer.prefHeight = command.envSize * command.agentSize
    val cons1 = new RowConstraints
    cons1.vgrow = Priority.Never

    val cons2 = new RowConstraints
    cons2.vgrow = Priority.Always

    mainContainer.rowConstraints.addAll(cons1, cons2)
    stage.setScene(new Scene(mainContainer, 600, 600))
    stage.show()
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

object View {


  def defaultModel(): Model = {
    val command = new ParticlesMainCommand
    command.handleCommand(Array())
    Model(command.nbParticles, command.envSize, command.agentSize, command.speed, command.toroidal, command.equity)
  }

  def apply(model: Model) = new View(model)

  def main(args: Array[String]): Unit = {
    new Thread {
      override def run(): Unit = {
        Application.launch(classOf[View], args: _*)
      }
    }.start
    defaultModel.run
  }

}
