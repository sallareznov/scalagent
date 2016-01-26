package fil.iagl.idl.scalagent.wator

import fil.iagl.idl.scalagent.core._

import scala.util.Random

class Shark(val breed: Int, val starve: Int, val environment: Environment) extends Agent {

  var starvationCounter = 0
  var breedCounter = 0

  override def doIt(): Unit = {
    var nextPotentialTunaPosition: Option[Position] = None
    if (starvationCounter == starve) {
      AgentsShapes.removeAgent(this)
      environment.unmark(position.x, position.y)
    }
    else {
      nextPotentialTunaPosition = nextPosition(environment, AgentType.TUNA)
      nextPotentialTunaPosition match {
        case Some(x) => {
          // TODO eat
        }
        case None => {
          var nextPotentialFreePosition = nextPosition(environment, AgentType.NO_TYPE)
          nextPotentialFreePosition match {
            case Some(x) => {
              if (breedCounter == breed) {
                // TODO reproduction
              }
              else {

              }
              // TODO move
            }
            case None => {
              //
            }
          }
        }
      }
    }
  }

  def move(environment: Environment, position: Position): Unit = {
    if (position != null) {
      AgentsShapes.relocateShape(this, position)
    } else {
      val stepX = choices(Random.nextInt(3))
      val stepY = choices(Random.nextInt(3))
      val newX = if ((position.x + stepX) >= 0) position.x + stepX else (position.x + stepX) + environment.width
      val newY = if ((position.y + stepY) >= 0) position.y + stepY else (position.y + stepY) + environment.height
      val newPosition = Position(newX % environment.width, newY % environment.height)
      //TODO check newPosition is empty
      AgentsShapes.relocateShape(this, newPosition)
    }
  }

}

object Shark {

  def apply(breed: Int, starve: Int, environment: Environment) = new Shark(breed, starve, environment)

}
