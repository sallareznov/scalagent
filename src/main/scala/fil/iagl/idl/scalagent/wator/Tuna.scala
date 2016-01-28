package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color

import fil.iagl.idl.scalagent.core._

class Tuna(breed: Int, val environment: Environment) extends WatorAgent(breed) {

  agentType = AgentType.TUNA

  override def doIt(agentsShapes: AgentsShapes): Unit = {
    val nextPotentialPosition = nextFreePosition(environment)
    nextPotentialPosition match {
      case Some(x) => {
        val potentialChild = Tuna(breed, environment)
        moveAndAimToReproduce(environment, potentialChild, Color.ROSYBROWN, agentsShapes)
        position = nextPotentialPosition.get
        agentsShapes.relocateShape(this, position.x * 5, position.y * 5)
        environment.mark(position.x, position.y, this)
      }
      case None => breedCounter += 1
    }

  }

}

object Tuna {

  def apply(breed: Int, environment: Environment) = new Tuna(breed, environment)

}