package fil.iagl.idl.scalagent.wator

import java.util
import javafx.animation.{Timeline, KeyFrame}
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.{EventHandler, ActionEvent}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.{Rectangle, Circle}
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

class WatorView extends Application {

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((WatorCommand.width, WatorCommand.height) == (0, 0)) {
      WatorCommand.width = primScreenBounds.getWidth.toInt
      WatorCommand.height = primScreenBounds.getHeight.toInt
    }
    val model = new WatorModel(WatorCommand.width, WatorCommand.height, WatorCommand.nTunas, WatorCommand.nSharks, WatorCommand.tBreed, WatorCommand.sBreed, WatorCommand.starve)
    primaryStage.setTitle("Wator")
    val scene = new Scene(canvas, primScreenBounds.getWidth, primScreenBounds.getHeight)
    primaryStage.setScene(scene)
    primaryStage.show()
    val agentsCircles = FXCollections.observableArrayList(new util.ArrayList[Rectangle]())
    model.agents. foreach(agent => agentsCircles.addAll(agent.shape.get.asInstanceOf[Rectangle]))
    canvas.getChildren.addAll(agentsCircles)
    // TODO speed argument
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(15), new EventHandler[ActionEvent]() {
      def handle(actionEvent: ActionEvent): Unit = {
        model.run()
      }
    }))
    timelineLoop.setCycleCount(-1)
    timelineLoop.play()
  }
}

object WatorView {

  def main(args: Array[String]) {
    Application.launch(classOf[WatorView], args: _*)
  }

}
