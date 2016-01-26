package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

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

  for (i <- 0 until nTunas) {
    val tuna = Tuna(tBreed, environment)
    tuna.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += tuna.position
    val tunaShape = new Rectangle(8, 8, Color.GREEN)
    tunaShape.relocate(tuna.position.x, tuna.position.y)
    AgentsShapes.linkAgentToShape(tuna, tunaShape)
    environment.mark(tuna.position.x, tuna.position.y, AgentType.TUNA)
    agents += tuna
  }

  for (i <- 0 until nSharks) {
    val shark = Shark(sBreed, starve, environment)
    shark.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += shark.position
    val sharkShape = new Rectangle(8, 8, Color.RED)
    sharkShape.relocate(shark.position.x, shark.position.y)
    AgentsShapes.linkAgentToShape(shark, sharkShape)
    environment.mark(shark.position.x, shark.position.y, AgentType.SHARK)
    agents += shark
  }

  agents = Random.shuffle(agents)

  override def run(): Unit = {
    agents.foreach(_.doIt())
    val newAgents = AgentsShapes.agentsShapes.keySet &~ agents
    agents = agents ++= newAgents
    notifyObservers(newAgents)
  }
}
