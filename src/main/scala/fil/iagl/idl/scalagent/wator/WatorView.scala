package fil.iagl.idl.scalagent.wator

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.stage.{Screen, Stage}
import javafx.util.Duration

import fil.iagl.idl.scalagent.core.AgentsShapes

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
    //scene.setFill(Color.SKYBLUE)
    primaryStage.setScene(scene)
    primaryStage.show()
    AgentsShapes.agentsShapes.values.foreach(shape => canvas.getChildren.add(shape))
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
