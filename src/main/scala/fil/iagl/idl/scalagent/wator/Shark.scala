package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color

import fil.iagl.idl.scalagent.core._

import scala.util.Random

class Shark(breed: Int, val starve: Int, val environment: Environment) extends WatorAgent(breed) {

  agentType = AgentType.SHARK

  var starvationCounter = 0

  // TODO refactor heavily
  override def doIt(): Unit = {
    if (starvationCounter == starve) {
      AgentsShapes.removeAgent(this)
      environment.unmark(position.x, position.y)
    }
    else {
      val nextPotentialTunaPosition = nextPositionOccupiedByATuna(environment)
      nextPotentialTunaPosition match {
        case Some(pos) => {
          val tunaToBeEaten = environment.getAgent(pos.x, pos.y).get
          AgentsShapes.removeAgent(tunaToBeEaten)
          environment.unmark(tunaToBeEaten.position.x, tunaToBeEaten.position.y)
          tunaToBeEaten.isVisited = true
          val potentialChild = Shark(breed, starve, environment)
          moveAndAimToReproduce(environment, potentialChild, Color.SKYBLUE)
          position = pos
          AgentsShapes.relocateShape(this, position.x * 5, position.y * 5)
          environment.mark(position.x, position.y, this)
          starvationCounter = 0
        }
        case None => {
          val nextPotentialFreePosition = nextFreePosition(environment)
          nextPotentialFreePosition match {
            case Some(x) => {
              val potentialChild = Shark(breed, starve, environment)
              moveAndAimToReproduce(environment, potentialChild, Color.SKYBLUE)
              position = nextPotentialFreePosition.get
              AgentsShapes.relocateShape(this, position.x * 5, position.y * 5)
              environment.mark(position.x, position.y, this)
              starvationCounter += 1
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

  def nextPositionOccupiedByATuna(environment: Environment): Option[Position] = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))

    list1.foreach(i => list2.foreach(j => {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val newPosition = Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height)
      environment.getAgent(newPosition.x, newPosition.y) match {
        case Some(agent) if agent.agentType == AgentType.TUNA => return Some(newPosition)
        case _ => ()
      }
    }))
    None
  }
}

object Shark {

  def apply(breed: Int, starve: Int, environment: Environment) = new Shark(breed, starve, environment)

}
