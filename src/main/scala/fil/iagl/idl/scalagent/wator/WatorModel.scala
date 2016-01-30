package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._

import scala.collection.mutable.ListBuffer
import scala.util.Random

class WatorModel(val width: Int,
                 val height: Int,
                 val nTunas: Int,
                 val nSharks: Int,
                 val tBreed: Int,
                 val sBreed: Int,
                 val starve: Int) extends Model with Observable {

  val environment = Environment(width, height)
  val alreadyTakenPositions = ListBuffer[Position]()

  for (i <- 0 until nTunas) {
    val tuna = Tuna(tBreed, environment)
    tuna.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += tuna.position
    val tunaShape = new Circle(2.5, Color.ROSYBROWN)
    tunaShape.relocate(tuna.position.x * 4, tuna.position.y * 4)
    agentsShapes.linkAgentToShape(tuna, tunaShape)
    environment.mark(tuna.position.x, tuna.position.y, tuna)
    agents += tuna
  }

  for (i <- 0 until nSharks) {
    val shark = Shark(sBreed, starve, environment)
    shark.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += shark.position
    val sharkShape = new Circle(2.5, Color.BLUE)
    sharkShape.relocate(shark.position.x * 4, shark.position.y * 4)
    agentsShapes.linkAgentToShape(shark, sharkShape)
    environment.mark(shark.position.x, shark.position.y, shark)
    agents += shark
  }

  agents = Random.shuffle(agents)

  override def run(): Unit = {
    agents.foreach(agent => {
      if (!agent.isVisited) {
        agent.doIt(agentsShapes)
      }
    })
    val newAgents = agentsShapes.agentsToShapesAssociations.keySet &~ agents
    val deletedAgents = agents &~ agentsShapes.agentsToShapesAssociations.keySet
    agents = agents --= deletedAgents
    agents = agents ++= newAgents
    val deletedShapes = agentsShapes.trash
    notifyObservers(newAgents, deletedShapes)
    agentsShapes.emptyTrash()
  }
}
