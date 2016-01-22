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

class ParticlesView extends Application {

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((ParticlesMainCommand.envWidth, ParticlesMainCommand.envHeight) == (0, 0)) {
      ParticlesMainCommand.envWidth = primScreenBounds.getWidth.toInt
      ParticlesMainCommand.envHeight = primScreenBounds.getHeight.toInt
    }
    /*if (ParticlesMainCommand.visibility) {
      val gc = canvas.getGraphicsContext2D()
      canvas.getChildren.add(new Line)
      gc.setLineWidth(1.0)
      for (x <- 0 to ParticlesMainCommand.envWidth; x+=10) {
        x1 = if (sharp) x + 0.5 else x
        gc.moveTo(x1, 0)
        gc.lineTo(x1, height)
        gc.stroke()
      }
    }*/
    val model = new ParticlesModel(ParticlesMainCommand.nbParticles, ParticlesMainCommand.envWidth, ParticlesMainCommand.envHeight, ParticlesMainCommand.agentSize, ParticlesMainCommand.speed, ParticlesMainCommand.toroidal, ParticlesMainCommand.equity)
    primaryStage.setTitle("Particles")
    val scene = new Scene(canvas, primScreenBounds.getWidth, primScreenBounds.getHeight)
    primaryStage.setScene(scene)
    primaryStage.show()
    val agentsCircles = FXCollections.observableArrayList(new util.ArrayList[Circle]())
    model.agents.foreach(agent => agentsCircles.addAll(agent.shape.get.asInstanceOf[Circle]))
    canvas.getChildren.addAll(agentsCircles)
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(ParticlesMainCommand.speed), new EventHandler[ActionEvent]() {
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
