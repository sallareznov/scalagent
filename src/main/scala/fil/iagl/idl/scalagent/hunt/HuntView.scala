package fil.iagl.idl.scalagent.hunt

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.Pane
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.scalagent.core.Direction

class HuntView extends Application {

  val primScreenBounds = Screen.getPrimary.getVisualBounds
  if ((HuntCommand.envWidth, HuntCommand.envHeight) ==(0, 0)) {
    HuntCommand.envWidth = primScreenBounds.getWidth.toInt
    HuntCommand.envHeight = primScreenBounds.getHeight.toInt
  }
  val canvas = new Pane()
  val scene = new Scene(canvas, HuntCommand.envWidth, HuntCommand.envHeight)

  override def start(primaryStage: Stage): Unit = {
    val model = new HuntModel(HuntCommand.nbHunters, HuntCommand.nbObstacles, HuntCommand.envHeight, HuntCommand.envWidth, HuntCommand.agentSize)
    primaryStage.setTitle("Hunt")
    primaryStage.setScene(scene)
    primaryStage.show()
    model.agentsShapes.agentsToShapesAssociations.values.foreach(shape => canvas.getChildren.add(shape))
    scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler[KeyEvent] {
      override def handle(event: KeyEvent): Unit = {
        event.getCode match {
          case KeyCode.UP => model.prey.direction = Direction.NORTH
          case KeyCode.DOWN => model.prey.direction = Direction.SOUTH
          case KeyCode.RIGHT => model.prey.direction = Direction.EAST
          case KeyCode.LEFT => model.prey.direction = Direction.WEST
          case _ => ()
        }
      }
    })
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(HuntCommand.speed), new EventHandler[ActionEvent]() {
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
