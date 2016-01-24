package fil.iagl.idl.scalagent.particles

import java.util
import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.Circle
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.scalagent.core.AgentsShapes

// TODO Evolutionary curve of the execution time of the application depending on the number of particles
// TODO - without any interaction between them
// TODO - with an interaction between them
class ParticlesView extends Application {

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((ParticlesCommand.envWidth, ParticlesCommand.envHeight) == (0, 0)) {
      ParticlesCommand.envWidth = primScreenBounds.getWidth.toInt
      ParticlesCommand.envHeight = primScreenBounds.getHeight.toInt
    }
    val model = new ParticlesModel(ParticlesCommand.nbParticles, ParticlesCommand.envWidth, ParticlesCommand.envHeight, ParticlesCommand.agentSize, ParticlesCommand.speed, ParticlesCommand.toroidal, ParticlesCommand.equity)
    primaryStage.setTitle("Particles")
    val scene = new Scene(canvas, primScreenBounds.getWidth, primScreenBounds.getHeight)
    primaryStage.setScene(scene)
    primaryStage.show()
    AgentsShapes.agentsShapes.values.foreach(shape => canvas.getChildren.add(shape))
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
