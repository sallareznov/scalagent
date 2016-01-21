package fil.iagl.idl.scalagent.particles

import java.util
import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.collections.{FXCollections, ObservableList}
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.{Circle, Shape}
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.scalagent.base.{Agent, Observer}

import scala.collection.JavaConverters._

class ParticlesView extends Application with Observer {

  val canvas = new Pane()
  var circles: Option[ObservableList[Shape]] = None

  override def start(primaryStage: Stage): Unit = {
    val command = new ParticlesMainCommand()
    command.handleCommand(getParameters.getRaw().asScala.toList.toArray)
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    val model = new ParticlesModel(command.nbParticles, command.envSize, command.agentSize, command.speed, command.toroidal, command.equity)
    primaryStage.setTitle("Particles")
    // TODO primScreenBounds.getWidth(), primScreenBounds.getHeight()
    val scene = new Scene(canvas, model.envSize, model.envSize)
    primaryStage.setScene(scene)
    primaryStage.show()
    val agentsCircles = model.agents.map(agent => agent.shape.get)
    circles = Some(FXCollections.observableArrayList(new util.ArrayList[Circle]()))
    agentsCircles.foreach(shape => canvas.getChildren.add(shape))
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler[ActionEvent]() {
      def handle(actionEvent: ActionEvent): Unit = {
        model.run()
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

object ParticlesView {

  def main (args: Array[String]) {
    Application.launch(classOf[ParticlesView], args: _*)
  }

}
