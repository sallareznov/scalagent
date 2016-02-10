package fil.iagl.idl.scalagent.hunt.view

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.Pane
import javafx.stage.{Screen, Stage}
import javafx.util.Duration
import fil.iagl.idl.scalagent.core.util.Direction
import fil.iagl.idl.scalagent.hunt.model.HuntModel
import fil.iagl.idl.scalagent.hunt.util.HuntCommandOptions

class HuntView extends Application {

  val primScreenBounds = Screen.getPrimary.getVisualBounds
  if ((HuntCommandOptions.envWidth, HuntCommandOptions.envHeight) ==(0, 0)) {
    HuntCommandOptions.envWidth = primScreenBounds.getWidth.toInt
    HuntCommandOptions.envHeight = primScreenBounds.getHeight.toInt
  }
  val canvas = new Pane()
  val scene = new Scene(canvas, HuntCommandOptions.envWidth * 5, HuntCommandOptions.envHeight * 5)

  override def start(primaryStage: Stage): Unit = {
    val model = new HuntModel(HuntCommandOptions.nbHunters, HuntCommandOptions.nbObstacles, HuntCommandOptions.envHeight, HuntCommandOptions.envWidth, HuntCommandOptions.agentSize)
    model.init()
    primaryStage.setTitle("Hunt")
    primaryStage.setScene(scene)
    primaryStage.show()

    // TODO labels drawing takes too much time... Is there another solution?
    //DijkstraDistancesLabels.initLabels(HuntCommand.envWidth, HuntCommand.envHeight)
    //DijkstraDistancesLabels.labels.get.foreach(_.foreach(label => canvas.getChildren.add(label)))
    model.agentsShapes.agentsToShapesAssociations.values.foreach(shape => canvas.getChildren.add(shape))
    // TODO use the controller
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
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(HuntCommandOptions.speed), new EventHandler[ActionEvent]() {
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
