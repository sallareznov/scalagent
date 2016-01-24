package fil.iagl.idl.scalagent.core

import javafx.scene.shape.Shape

object AgentsShapes {

  val agentsShapes = scala.collection.mutable.Map[Agent, Shape]()

  def linkAgentToShape(agent: Agent, shape: Shape): Unit = {
    agentsShapes += (agent -> shape)
  }

  def relocateShape(agent: Agent, newPosition: Position): Unit = {
    agentsShapes(agent).relocate(newPosition.x, newPosition.y)
  }

}
