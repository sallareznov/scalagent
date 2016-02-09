package fil.iagl.idl.scalagent.wator.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._
import fil.iagl.idl.scalagent.core.model.{Model, Environment}
import fil.iagl.idl.scalagent.core.util.{Position, Observable, MooreNeighborhood}

import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
  * The model of the wator environment in a MVC paradigm
  * @constructor constructs a new WatorModel with `width`, `height`, `nTunas`, `nSharks`, `tBreed`, `sBreed` and `starve`
  * @param width the width of the environment
  * @param height the height of the environment
  * @param nTunas the initial number of tunas in the environment
  * @param nSharks the initial number of sharks in the environment
  * @param tBreed the number of cycles tunas must exists before reproducing
  * @param sBreed the number of cycles sharks must exists before reproducing
  * @param starve the number of cycles a shark has to find food before starving
  */
class WatorModel(val width: Int,
                 val height: Int,
                 val nTunas: Int,
                 val nSharks: Int,
                 val tBreed: Int,
                 val sBreed: Int,
                 val starve: Int) extends Model with Observable {

  val environment = Environment(width, height)
  val alreadyTakenPositions = ListBuffer[Position]()

  def init(): Unit = {
    // add nTunas tunas to the model
    for (i <- 0 until nTunas) {
      val tuna = Tuna(tBreed, environment)
      tuna.position = Position(Random.nextInt(width), Random.nextInt(height))
      alreadyTakenPositions += tuna.position
      val tunaShape = new Circle(2.5, Color.ROSYBROWN)
      tunaShape.relocate(tuna.position.x * 4, tuna.position.y * 4)
      agentsShapes.linkAgentToShape(tuna, tunaShape)
      environment.mark(tuna.position.x, tuna.position.y, tuna)
      agents += tuna
    }

    // add nSharks sharks to the model
    for (i <- 0 until nSharks) {
      val shark = Shark(sBreed, starve, environment)
      shark.position = Position(Random.nextInt(width), Random.nextInt(height))
      alreadyTakenPositions += shark.position
      val sharkShape = new Circle(2.5, Color.BLUE)
      sharkShape.relocate(shark.position.x * 4, shark.position.y * 4)
      agentsShapes.linkAgentToShape(shark, sharkShape)
      environment.mark(shark.position.x, shark.position.y, shark)
      agents += shark
    }

    agents = Random.shuffle(agents)
  }

  override def run(): Unit = {
    agents.foreach(agent => {
      if (!agent.isVisited) {
        agent.doIt(new MooreNeighborhood(), agentsShapes)
      }
    })
    val newAgents = agentsShapes.agentsToShapesAssociations.keySet &~ agents
    val deletedAgents = agents &~ agentsShapes.agentsToShapesAssociations.keySet
    agents = agents --= deletedAgents
    agents = agents ++= newAgents
    val deletedShapes = agentsShapes.shapesToDelete
    notifyObservers(newAgents, deletedShapes)
    agentsShapes.deleteShapesToDelete()
  }
}
