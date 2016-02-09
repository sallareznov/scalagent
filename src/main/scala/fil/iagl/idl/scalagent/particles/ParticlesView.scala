package fil.iagl.idl.scalagent.particles

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

/**
  * View of the particles application
  */
class ParticlesView extends Application {

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((ParticlesCommand.envWidth, ParticlesCommand.envHeight) == (0, 0)) {
      ParticlesCommand.envWidth = primScreenBounds.getWidth.toInt
      ParticlesCommand.envHeight = primScreenBounds.getHeight.toInt
    }
    val model = new ParticlesModel(ParticlesCommand.nbParticles, ParticlesCommand.envWidth, ParticlesCommand.envHeight, ParticlesCommand.agentSize, ParticlesCommand.toroidal, ParticlesCommand.equity)
    primaryStage.setTitle("Particles")
    val scene = new Scene(canvas, ParticlesCommand.envWidth * 5, ParticlesCommand.envHeight * 5)
    primaryStage.setScene(scene)
    primaryStage.show()
    model.agentsShapes.agentsToShapesAssociations.values.foreach(shape => canvas.getChildren.add(shape))
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(ParticlesCommand.speed), new EventHandler[ActionEvent]() {
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
