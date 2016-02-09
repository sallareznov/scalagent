package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color

import fil.iagl.idl.scalagent.core._

/**
  * A tuna
  * @param breed the number of cycles the agent must exist before reproducing
  * @param environment the environment
  */
class Tuna(breed: Int, val environment: Environment) extends WatorAgent(breed) {

  agentType = AgentType.TUNA

  /**
    * the tuna looks for a free position in the given neighborhood.
    * If there is a free position, it moves to that position and aims to reproduce.
    * If there is no free position, it doesn't move and doesn't reproduce.
    * @param neighborhood the neighborhood
    * @param agentsShapes the shapes of the agents
    */
  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {
    val nextPotentialPosition = nextFreePosition(environment)
    nextPotentialPosition match {
      case Some(x) => {
        val potentialChild = Tuna(breed, environment)
        moveAndAimToReproduce(environment, potentialChild, Color.ROSYBROWN, agentsShapes)
        position = nextPotentialPosition.get
        agentsShapes.relocateShape(this, position.x * 4, position.y * 4)
        environment.mark(position.x, position.y, this)
      }
      case None => {
        breedCounter += 1
        age += 1
      }
    }

  }

}

/**
  * Companion object
  */
object Tuna {

  def apply(breed: Int, environment: Environment) = new Tuna(breed, environment)

}