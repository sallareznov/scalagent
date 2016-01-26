package fil.iagl.idl.scalagent.wator

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

import fil.iagl.idl.scalagent.core._

class Tuna(val breed: Int, val environment: Environment) extends Agent {

  var breedCounter = 0

  override def doIt(): Unit = {
    val nextPotentialPosition = nextPosition(environment, AgentType.NO_TYPE)
    nextPotentialPosition match {
      case Some(x) => {
        if (breedCounter == breed) {
          val child = Tuna(breed, environment)
          child.position = Position(position.x, position.y)
          val childShape = new Rectangle(8, 8, Color.GREEN)
          childShape.relocate(position.x, position.y)
          AgentsShapes.linkAgentToShape(child, childShape)
          breedCounter = 0
        }
        else {
          breedCounter += 1
          environment.unmark(position.x, position.y)
        }
        position = nextPotentialPosition.get
        AgentsShapes.relocateShape(this, position)
        environment.mark(position.x, position.y, AgentType.TUNA)
      }
      case None => ()
    }

  }

}

object Tuna {

  def apply(breed: Int, environment: Environment) = new Tuna(breed, environment)

}