package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.Observer

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

object View extends JFXApp with Observer {

  /*override def start(stage: javafx.stage.Stage): Unit = {
    stage.setTitle("Particles")
    stage.setWidth(envSize)
    stage.setHeight(envSize)
    stage.setScene(new Scene {
      content = mainContainer
      mainContainer.setMinSize(envSize, envSize)
    })
    JFXApp.STAGE = stage
    if (JFXApp.AUTO_SHOW) {
      JFXApp.STAGE.show()
    }
  }*/

  println("Constructor call")

  var mainContainer = new GridPane()
  println(s"mainContainer = ${mainContainer}")
  var envSize = 0
  var agentSize = 0
  var visibility = false

  stage = new JFXApp.PrimaryStage {
    title.value = "Particles"
    width = envSize
    height = envSize
    scene = new Scene {
      root = mainContainer
      mainContainer.setMinSize(envSize, envSize)
    }
  }

  println(s"mainContainer = ${mainContainer}")

  val commandWithHandling = new ParticlesMainCommand()
  commandWithHandling.handleCommand(Array("--equity"))

  override def update(takenCells: Array[Array[Boolean]]): Unit = {
    println(mainContainer)
    mainContainer.children.clear()
    for (i <- 0 until takenCells.length) {
      for (j <- 0 until takenCells(0).length) {
        if (takenCells(i)(j)) {
          val particle = new Rectangle {
            width = agentSize
            height = agentSize
            x = i
            y = j
            fill = Color.Red
          }
          mainContainer.add(particle, i, j)

        }
      }
    }
  }

  def setEnvSize(envSize: Int): Unit = (this.envSize = envSize)

  def setAgentSize(agentSize: Int): Unit = (this.agentSize = agentSize)

  def setVisibility(visibility: Boolean): Unit = (this.visibility = visibility)

}
