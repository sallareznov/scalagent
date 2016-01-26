package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color

import fil.iagl.idl.scalagent.core._

class Shark(breed: Int, val starve: Int, val environment: Environment) extends BreedingAgent(breed) {

  var starvationCounter = 0

  override def doIt(): Unit = {
    // TODO solution to get access to the agent object in order to remove it from the canvas
    val nextPotentialTunaPosition = nextPosition(environment, AgentType.TUNA)
    if (starvationCounter == starve) {
      AgentsShapes.removeAgent(this)
      environment.unmark(position.x, position.y)
    }
    else {
      nextPotentialTunaPosition match {
        case Some(x) => {
          //environment.unmark(position.x, position.y)
          //AgentsShapes.
        }
        case None => {
          val nextPotentialFreePosition = nextPosition(environment, AgentType.NO_TYPE)
          nextPotentialFreePosition match {
            case Some(x) => {
              val potentialChild = Shark(breed, starve, environment)
              moveAndAimToReproduce(environment, potentialChild, Color.RED)
              position = nextPotentialFreePosition.get
              AgentsShapes.relocateShape(this, position)
              environment.mark(position.x, position.y, AgentType.SHARK)
              starvationCounter += 1
              breedCounter += 1
            }
            case None => {
              starvationCounter += 1
              breedCounter += 1
            }
          }
        }
      }
    }
  }
}

object Shark {

  def apply(breed: Int, starve: Int, environment: Environment) = new Shark(breed, starve, environment)

}
