package fil.iagl.idl.scalagent.wator

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.chart.{AreaChart, NumberAxis, XYChart}
import javafx.scene.layout.{BorderPane, Pane}
import javafx.scene.shape.Shape
import javafx.stage.{Screen, Stage, WindowEvent}
import javafx.util.Duration

import fil.iagl.idl.scalagent.core.{Agent, AgentsShapes, Observer}

import scala.collection.mutable

class WatorView extends Application with Observer {

  val canvas = new Pane()
  var nTunas = 0
  var nSharks = 0

  override def start(primaryStage: Stage): Unit = {
    val primScreenBounds = Screen.getPrimary.getVisualBounds
    if ((WatorCommand.width, WatorCommand.height) == (0, 0)) {
      WatorCommand.width = primScreenBounds.getWidth.toInt
      WatorCommand.height = primScreenBounds.getHeight.toInt
    }
    val model = new WatorModel(WatorCommand.width, WatorCommand.height, WatorCommand.nTunas, WatorCommand.nSharks, WatorCommand.tBreed, WatorCommand.sBreed, WatorCommand.starve)
    nTunas = WatorCommand.nTunas
    nSharks = WatorCommand.nSharks
    model.addObserver(this)
    primaryStage.setTitle("Wator")
    val root = new BorderPane()
    root.setCenter(canvas)
    val xAxis = new NumberAxis()
    val yAxis = new NumberAxis()
    val areaChart = new AreaChart[Number, Number](xAxis, yAxis)
    areaChart.setTitle("Time-dependent number of tunas and sharks")
    val tunasSeries = new XYChart.Series[Number, Number]()
    tunasSeries.setName("Tunas")
    tunasSeries.getData.add(new XYChart.Data[Number, Number](0, WatorCommand.nTunas))
    val sharksSeries = new XYChart.Series[Number, Number]()
    sharksSeries.setName("Sharks")
    sharksSeries.getData.add(new XYChart.Data[Number, Number](0, WatorCommand.nTunas))
    root.setRight(areaChart)
    val scene = new Scene(root, primScreenBounds.getWidth.toInt, primScreenBounds.getWidth.toInt)
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
    var elapsedSeconds = 1
    val updateChartLoop = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler[ActionEvent]() {
      override def handle(actionEvent: ActionEvent): Unit = {
      //  areaChart.getData.add(new XYChart.Data[Number, Number](elapsedSeconds, model.)))
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
