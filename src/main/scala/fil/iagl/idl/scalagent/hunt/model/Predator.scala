package fil.iagl.idl.scalagent.hunt.model

import javafx.application.Platform

import fil.iagl.idl.scalagent.core._
import fil.iagl.idl.scalagent.core.model.{Environment, Agent}
import fil.iagl.idl.scalagent.core.util.{Position, Neighborhood, AgentType, AgentsShapes}
import fil.iagl.idl.scalagent.hunt.util.DijkstraPathFinder

import scala.util.Random

class Predator(val environment: Environment, val dijkstraPathFinder: DijkstraPathFinder) extends Agent {

  agentType = AgentType.PREDATOR

  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))
    var lowestDijkstraValue = Int.MaxValue
    var optimalNextPosition: Option[Position] = None
    list1.foreach(i => list2.foreach(j => {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val neighbor = Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height)
      if (environment.isFree(neighbor.x, neighbor.y)) {
        val neighborDistance = dijkstraPathFinder.currentDijkstraDistancesOption.get(neighbor.x)(neighbor.y)
        if (neighborDistance < lowestDijkstraValue) {
          lowestDijkstraValue = neighborDistance
          optimalNextPosition = Some(neighbor)
        }
      }
      else if (environment.getAgent(neighbor.x, neighbor.y).get.agentType == AgentType.PREY) {
        println("GAME OVER")
        Platform.exit()
      }
    }))
    position = optimalNextPosition.get
    agentsShapes.relocateShape(this, position.x * 5, position.y * 5)
  }

}

object Predator {

  def apply(environment: Environment, dijkstraPathFinder: DijkstraPathFinder) = new Predator(environment, dijkstraPathFinder)

}
