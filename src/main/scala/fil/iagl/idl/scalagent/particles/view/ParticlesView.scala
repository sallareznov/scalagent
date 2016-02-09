package fil.iagl.idl.scalagent.particles.view

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.{Screen, Stage}
import javafx.util.Duration
import fil.iagl.idl.scalagent.particles.model.ParticlesModel
import fil.iagl.idl.scalagent.particles.util.ParticlesCommandOptions

/**
  * View of the particles application
  */
class ParticlesView extends Application {

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((ParticlesCommandOptions.envWidth, ParticlesCommandOptions.envHeight) == (0, 0)) {
      ParticlesCommandOptions.envWidth = primScreenBounds.getWidth.toInt
      ParticlesCommandOptions.envHeight = primScreenBounds.getHeight.toInt
    }
    val model = new ParticlesModel(ParticlesCommandOptions.nbParticles, ParticlesCommandOptions.envWidth, ParticlesCommandOptions.envHeight, ParticlesCommandOptions.agentSize, ParticlesCommandOptions.toroidal, ParticlesCommandOptions.equity)
    primaryStage.setTitle("Particles")
    val scene = new Scene(canvas, ParticlesCommandOptions.envWidth * 5, ParticlesCommandOptions.envHeight * 5)
    primaryStage.setScene(scene)
    primaryStage.show()
    model.agentsShapes.agentsToShapesAssociations.values.foreach(shape => canvas.getChildren.add(shape))
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(ParticlesCommandOptions.speed), new EventHandler[ActionEvent]() {
      def handle(actionEvent: ActionEvent): Unit = {
        model.run()
      }
    }))
    timelineLoop.setCycleCount(-1)
    timelineLoop.play()
  }

}

object ParticlesView {

  def main (args: Array[String]) {
    Application.launch(classOf[ParticlesView], args: _*)
  }

}
