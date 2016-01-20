package fil.iagl.idl.scalagent.particles

import javafx.animation.{Timeline, KeyFrame}
import javafx.application.Application
import javafx.collections.{ObservableList, FXCollections}
import javafx.event.{EventHandler, ActionEvent}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.Circle
import javafx.stage.Stage
import javafx.util.Duration

import fil.iagl.idl.scalagent.base.Environment


class ViewFX extends Application {

  val size = 1000
  val canvas = new Pane()
  val circles = List[Circle]()
  //var circlesObs = ObservableList[List[Circle]]()

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Particles22")
    val scene = new Scene(canvas, size, size)
    val environment = Environment(size)
    primaryStage.setScene(scene)
    primaryStage.show
    //val model = Model

    //canvas.getChildrenUnmodifiable.addAll(circles)
    canvas.getChildren().size
    //circlesObs = FXCollections.observableArrayList(circles)
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler[ActionEvent]() {
      def handle(actionEvent: ActionEvent): Unit = {

      }
    }))

    timelineLoop.setCycleCount(-1)
    timelineLoop.play()

  }

  def main (args: Array[String]) {
    Application.launch(classOf[ViewFX], args: _*)
  }

}
