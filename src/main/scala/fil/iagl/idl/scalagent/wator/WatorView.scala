package fil.iagl.idl.scalagent.wator

import javafx.animation.{KeyFrame, Timeline}
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.{Orientation, Pos}
import javafx.scene.Scene
import javafx.scene.chart._
import javafx.scene.control.SplitPane
import javafx.scene.layout._
import javafx.scene.shape.Shape
import javafx.stage.{Screen, Stage, WindowEvent}
import javafx.util.Duration

import fil.iagl.idl.scalagent.core.{Agent, AgentType, Observer}

import scala.collection.mutable

class WatorView extends Application with Observer {

  val canvas = new Pane()
  val primScreenBounds = Screen.getPrimary.getVisualBounds
  if ((WatorCommand.width, WatorCommand.height) == (0, 0)) {
    WatorCommand.width = primScreenBounds.getWidth.toInt
    WatorCommand.height = primScreenBounds.getHeight.toInt
  }
  val model = new WatorModel(WatorCommand.width, WatorCommand.height, WatorCommand.nTunas, WatorCommand.nSharks, WatorCommand.tBreed, WatorCommand.sBreed, WatorCommand.starve)
  model.addObserver(this)
  val tunasSeriesLineChart = new XYChart.Series[Number, Number]()
  val sharksSeriesLineChart = new XYChart.Series[Number, Number]()
  val tunasSeriesBarChart = new XYChart.Series[String, Number]()
  val sharksSeriesBarChart = new XYChart.Series[String, Number]()

  WatorMetricsData.nTunas = WatorCommand.nTunas
  WatorMetricsData.nSharks = WatorCommand.nSharks

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Wator")
    val root = new SplitPane
    val chartsContainer = new SplitPane
    chartsContainer.setOrientation(Orientation.VERTICAL)
    val xAxisLineChart = new NumberAxis
    val yAxisLineChart = new NumberAxis
    xAxisLineChart.setLabel("Elapsed time")
    yAxisLineChart.setLabel("Number of fishes")
    val lineChart = new LineChart[Number, Number](xAxisLineChart, yAxisLineChart)
    val xAxisBarChart = new CategoryAxis
    val yAxisBarChart = new NumberAxis
    xAxisBarChart.setLabel("Age")
    yAxisBarChart.setLabel("Number of fishes")
    val barChart = new BarChart[String, Number](xAxisBarChart, yAxisBarChart)
    barChart.setTitle("Population chart of fishes")
    tunasSeriesBarChart.setName("Tunas")
    sharksSeriesBarChart.setName("Sharks")
    barChart.getData.addAll(tunasSeriesBarChart, sharksSeriesBarChart)

    val box = new VBox
    box.setAlignment(Pos.CENTER)
    val canvasContainer = new HBox
    canvasContainer.getChildren.add(canvas)
    canvasContainer.setAlignment(Pos.CENTER)
    box.getChildren.add(canvasContainer)
    chartsContainer.getItems.addAll(lineChart, barChart)
    root.getItems.addAll(box, chartsContainer)
    lineChart.setTitle("Time-dependent number of fishes")
    lineChart.setCreateSymbols(false)
    tunasSeriesLineChart.setName("Tunas")
    tunasSeriesLineChart.getData.add(new XYChart.Data[Number, Number](0, WatorCommand.nTunas))
    sharksSeriesLineChart.setName("Sharks")
    sharksSeriesLineChart.getData.add(new XYChart.Data[Number, Number](0, WatorCommand.nSharks))
    lineChart.getData.addAll(tunasSeriesLineChart, sharksSeriesLineChart)
    val scene = new Scene(root, primScreenBounds.getWidth.toInt, primScreenBounds.getWidth.toInt)
    primaryStage.setScene(scene)
    primaryStage.show()
    primaryStage.setOnCloseRequest(new EventHandler[WindowEvent]() {
      override def handle(event: WindowEvent): Unit = {
        // TODO get the number of laps every agent lasts and write it on a file (for the age pyramid)
      }
    })
    val agentsShapes = model.agentsShapes
    agentsShapes.agentsToShapesAssociations.values.foreach(shape => canvas.getChildren.add(shape))
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
        println(elapsedSeconds + " | " + "t : " + WatorMetricsData.nTunas + ", " + "s : " + WatorMetricsData.nSharks)
        tunasSeriesLineChart.getData.add(new XYChart.Data[Number, Number](elapsedSeconds, WatorMetricsData.nTunas))
        sharksSeriesLineChart.getData.add(new XYChart.Data[Number, Number](elapsedSeconds, WatorMetricsData.nSharks))
        elapsedSeconds += 1

      }
    }))
    updateChartLoop.setCycleCount(-1)
    updateChartLoop.play()


    val updateBarLoop = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit = {
        val agents = model.agents.asInstanceOf[mutable.HashSet[WatorAgent]]
        val tunasMap = mutable.Map[Int, Int]()
        val sharksMap = mutable.Map[Int, Int]()
        agents.foreach(agent => {
          agent.agentType match {
            case AgentType.TUNA => tunasMap.put(agent.age / 20, tunasMap.getOrElse(agent.age / 20, 0) + 1)
            case AgentType.SHARK => sharksMap.put(agent.age / 20, sharksMap.getOrElse(agent.age / 20, 0) + 1)
            case _ => ()
          }
        })
        val sortedTunas = mutable.ListMap(tunasMap.toSeq.sortBy(_._1):_*)
        val sortedSharks = mutable.ListMap(sharksMap.toSeq.sortBy(_._1):_*)
        tunasSeriesBarChart.getData.clear()
        sharksSeriesBarChart.getData.clear()
        sortedTunas.foreach(assoc => tunasSeriesBarChart.getData.add(new XYChart.Data(assoc._1.toString, assoc._2)))
        sortedSharks.foreach(assoc => sharksSeriesBarChart.getData.add(new XYChart.Data(assoc._1.toString, assoc._2)))
      }
    }))
    updateBarLoop.setCycleCount(-1)
    updateBarLoop.play()
  }

  override def update(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit = {
    newAgents.foreach(newAgent => canvas.getChildren.add(model.agentsShapes.agentsToShapesAssociations.get(newAgent).get))
    deletedShapes.foreach(shape => canvas.getChildren.remove(shape))
  }
}

object WatorView {

  def main(args: Array[String]) {
    Application.launch(classOf[WatorView], args: _*)
  }

}
