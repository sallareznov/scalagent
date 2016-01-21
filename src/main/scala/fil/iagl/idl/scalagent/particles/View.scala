package fil.iagl.idl.scalagent.particles

import java.util
import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.collections.{FXCollections, ObservableList}
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.stage.Stage
import javafx.util.Duration

import fil.iagl.idl.scalagent.base.{Agent, Observer}

import scala.collection.JavaConverters._

class View extends Application with Observer {

  val canvas = new Pane()
  val circles = new util.ArrayList[Circle]()
  var circlesObs: Option[ObservableList[Circle]] = None

  override def start(primaryStage: Stage): Unit = {
    val command = new ParticlesMainCommand()
    command.handleCommand(getParameters.getRaw().asScala.toList.toArray)
    val model = new Model(command.nbParticles, command.envSize, command.agentSize, command.speed, command.toroidal, command.equity)
    primaryStage.setTitle("Particles")
    val scene = new Scene(canvas, model.envSize, model.envSize)
    primaryStage.setScene(scene)
    primaryStage.show()
    val circle = new Circle(2.5, Color.RED)
    circle.relocate(200, 200)
    circles.add(circle)
    circlesObs = Some(FXCollections.observableArrayList(circles))
    canvas.getChildren.addAll(circlesObs.get)
    var (x, y) = (200, 200)
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler[ActionEvent]() {
      def handle(actionEvent: ActionEvent): Unit = {
        circle.relocate(x, y)
        x += 1
        y += 1
      }
    }))

    timelineLoop.setCycleCount(-1)
    timelineLoop.play()

  }

  override def update(agents: Array[Agent]): Unit = {

  }

  def run(): Unit = {
  }

}

object View {

  def main (args: Array[String]) {
    Application.launch(classOf[View], args: _*)
  }

}
