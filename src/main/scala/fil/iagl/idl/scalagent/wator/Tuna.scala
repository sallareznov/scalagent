package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color

import fil.iagl.idl.scalagent.core._

class Tuna(breed: Int, val environment: Environment) extends BreedingAgent(breed) {

  override def doIt(): Unit = {
    val nextPotentialPosition = nextPosition(environment, AgentType.NO_TYPE)
    nextPotentialPosition match {
      case Some(x) => {
        val potentialChild = Tuna(breed, environment)
        moveAndAimToReproduce(environment, potentialChild, Color.GREEN)
        position = nextPotentialPosition.get
        AgentsShapes.relocateShape(this, position)
        environment.mark(position.x, position.y, AgentType.TUNA)
      }
      case None => breedCounter += 1
    }

  }

}

object Tuna {

  def apply(breed: Int, environment: Environment) = new Tuna(breed, environment)

}