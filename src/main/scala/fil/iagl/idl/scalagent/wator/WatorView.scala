package fil.iagl.idl.scalagent.wator

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.Shape
import javafx.stage.{Screen, Stage, WindowEvent}
import javafx.util.Duration

import fil.iagl.idl.scalagent.core.{Agent, AgentsShapes, Observer}

import scala.collection.mutable

class WatorView extends Application with Observer {

  val canvas = new Pane()

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((WatorCommand.width, WatorCommand.height) == (0, 0)) {
      WatorCommand.width = primScreenBounds.getWidth.toInt
      WatorCommand.height = primScreenBounds.getHeight.toInt
    }
    val model = new WatorModel(WatorCommand.width, WatorCommand.height, WatorCommand.nTunas, WatorCommand.nSharks, WatorCommand.tBreed, WatorCommand.sBreed, WatorCommand.starve)
    model.addObserver(this)
    primaryStage.setTitle("Wator")
    val scene = new Scene(canvas, WatorCommand.width * 5, WatorCommand.height * 5)
    primaryStage.setScene(scene)
    primaryStage.show()
    primaryStage.setOnCloseRequest(new EventHandler[WindowEvent]() {
      override def handle(event: WindowEvent): Unit = {
        // TODO get the number of laps every agent lasts and write it on a file (for the age pyramid)
      }
    })
    AgentsShapes.agentsShapes.values.foreach(shape => canvas.getChildren.add(shape))
    val timelineLoop = new Timeline(new KeyFrame(Duration.millis(WatorCommand.speed), new EventHandler[ActionEvent]() {
      override def handle(actionEvent: ActionEvent): Unit = {
        model.run()
      }
    }))
    timelineLoop.setCycleCount(-1)
    timelineLoop.play()
  }

  override def update(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit = {
    newAgents.foreach(newAgent => canvas.getChildren.add(AgentsShapes.agentsShapes.get(newAgent).get))
    deletedShapes.foreach(shape => canvas.getChildren.remove(shape))
  }
}

object WatorView {

  def main(args: Array[String]) {
    Application.launch(classOf[WatorView], args: _*)
  }

}
