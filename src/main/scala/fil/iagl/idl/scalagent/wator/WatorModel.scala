package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

import fil.iagl.idl.scalagent.core._

import scala.collection.mutable.ListBuffer
import scala.util.Random

class WatorModel(val width: Int,
                 val height: Int,
                 val nTunas: Int,
                 val nSharks: Int,
                 val tBreed: Int,
                 val sBreed: Int,
                 val starve: Int) extends Model {

  override var environment = Environment(width, height)
  override var agents = ListBuffer[Agent]()
  val alreadyTakenPositions = ListBuffer[Position]()

  for (i <- 0 until nTunas) {
    val tuna = Tuna(tBreed)
    tuna.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += tuna.position
    val tunaShape = new Rectangle(8, 8, Color.GREEN)
    tunaShape.relocate(tuna.position.x, tuna.position.y)
    AgentsShapes.linkAgentToShape(tuna, tunaShape)
    environment.mark(tuna.position)
    agents += tuna
  }

  for (i <- 0 until nSharks) {
    val shark = Shark()
    shark.position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += shark.position
    val sharkShape = new Rectangle(8, 8, Color.RED)
    sharkShape.relocate(shark.position.x, shark.position.y)
    AgentsShapes.linkAgentToShape(shark, sharkShape)
    environment.mark(shark.position)
    agents += shark
  }

  agents = Random.shuffle(agents)

  override def run(): Unit = {
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(environment)
      environment.unmark(agentOldPosition)
      environment.mark(agent.position)
    })
  }
}
