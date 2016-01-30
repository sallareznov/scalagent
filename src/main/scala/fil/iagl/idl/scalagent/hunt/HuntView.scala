package fil.iagl.idl.scalagent.hunt

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.scalagent.particles.ParticlesCommand

class HuntView extends Application {

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((HuntCommand.envWidth, HuntCommand.envHeight) ==(0, 0)) {
      HuntCommand.envWidth = primScreenBounds.getWidth.toInt
      HuntCommand.envHeight = primScreenBounds.getHeight.toInt
    }
    val model = new HuntModel(HuntCommand.nbHunters, HuntCommand.nbObstacles, HuntCommand.envHeight, HuntCommand.envWidth, HuntCommand.agentSize)
    primaryStage.setTitle("Hunting")
    val scene = new Scene(canvas, ParticlesCommand.envWidth, ParticlesCommand.envHeight)
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

object HuntView {

  def main(args: Array[String]) {
    Application.launch(classOf[HuntView], args: _*)
  }

}
