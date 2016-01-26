package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

import fil.iagl.idl.scalagent.core._

import scala.util.Random

abstract class BreedingAgent(val breed: Int) extends Agent {

  var breedCounter = 0

  def nextPosition(environment: Environment, wantedType: AgentType.Type): Option[Position] = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))

    list1.foreach(i => list2.foreach(j => {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val newPosition = Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height)
      if (environment.getType(newPosition.x, newPosition.y) == wantedType) {
        return Some(newPosition)
      }
    }))
    None
  }

  def moveAndAimToReproduce(environment: Environment, potentialChild: Agent, color: Color): Unit = {
    if (breedCounter == breed) {
      potentialChild.position = Position(position.x, position.y)
      val childShape = new Rectangle(8, 8, color)
      childShape.relocate(position.x, position.y)
      AgentsShapes.linkAgentToShape(potentialChild, childShape)
      breedCounter = 0
    }
    else {
      breedCounter += 1
      environment.unmark(position.x, position.y)
    }
  }

}
