package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color
import javafx.scene.shape.{Rectangle, Circle}

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

  override var environment: Environment = Environment(width, height)
  override var agents: Array[Agent] = new Array[Agent](nTunas + nSharks)
  val alreadyTakenPositions = ListBuffer[Position]()

  def fillAgentsArray(beginningIndex: Int, endIndex: Int): Unit = {

  }

  // TODO refactor !!!

  for (i <- 0 until nTunas) {
    agents(i) = Tuna()
    agents(i).position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += agents(i).position
    val agentPosition = agents(i).position
    agents(i).shape = Some(new Rectangle(8, 8, Color.SKYBLUE))
    agents(i).shape.get.relocate(agents(i).position.x, agents(i).position.y)
    environment.mark(agentPosition)
  }

  var j = false
  for (i <- 0 until nSharks) {
    agents(i + nTunas) = Shark()
    agents(i + nTunas).position = Position(Random.nextInt(width), Random.nextInt(height))
    // TODO taken positions
    alreadyTakenPositions += agents(i + nTunas).position
    val agentPosition = agents(i + nTunas).position
    agents(i + nTunas).shape = Some(new Rectangle(8, 8, Color.RED))
    agents(i + nTunas).shape.get.relocate(agents(i + nTunas).position.x, agents(i + nTunas).position.y)
    environment.mark(agentPosition)
  }

  agents = Random.shuffle(agents.toList).toArray

  override def run(): Unit = {
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(environment)
      environment.unmark(agentOldPosition)
      environment.mark(agent.position)
    })
  }
}
