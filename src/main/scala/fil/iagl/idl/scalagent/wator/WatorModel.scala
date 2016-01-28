package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color
import javafx.scene.shape.{Circle, Rectangle}

import fil.iagl.idl.scalagent.core._

import scala.collection.mutable
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
  override var agents = mutable.HashSet[Agent]()
  val alreadyTakenPositions = ListBuffer[Position]()
  // TODO for the graphs
  var nbTunas = nTunas
  var nbSharks = nSharks

  for (i <- 0 until nTunas) {
    val tuna = Tuna(tBreed, environment)
    tuna.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += tuna.position
    val tunaShape = new Circle(2.5, Color.ROSYBROWN)
    tunaShape.relocate(tuna.position.x * 5, tuna.position.y * 5)
    AgentsShapes.linkAgentToShape(tuna, tunaShape)
    environment.mark(tuna.position.x, tuna.position.y, tuna)
    agents += tuna
  }

  for (i <- 0 until nSharks) {
    val shark = Shark(sBreed, starve, environment)
    shark.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += shark.position
    val sharkShape = new Circle(2.5, Color.SKYBLUE)
    sharkShape.relocate(shark.position.x * 5, shark.position.y * 5)
    AgentsShapes.linkAgentToShape(shark, sharkShape)
    environment.mark(shark.position.x, shark.position.y, shark)
    agents += shark
  }

  agents = Random.shuffle(agents)

  override def run(): Unit = {
    agents.foreach(agent => {
      if (!agent.isVisited) {
        agent.doIt()
      }
    })
    val newAgents = AgentsShapes.agentsShapes.keySet &~ agents
    val deletedAgents = agents &~ AgentsShapes.agentsShapes.keySet
    nbTunas += newAgents.size - deletedAgents.size
    agents = agents --= deletedAgents
    agents = agents ++= newAgents
    val deletedShapes = AgentsShapes.trash
    notifyObservers(newAgents, deletedShapes)
    AgentsShapes.emptyTrash()
  }
}
