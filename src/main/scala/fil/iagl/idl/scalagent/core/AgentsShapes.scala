package fil.iagl.idl.scalagent.core

import javafx.scene.shape.Shape

import scala.collection.mutable

class AgentsShapes {

  val agentsToShapesAssociations = mutable.Map[Agent, Shape]()
  val trash = mutable.HashSet [Shape]()

  def linkAgentToShape(agent: Agent, shape: Shape): Unit = agentsToShapesAssociations += (agent -> shape)

  def relocateShape(agent: Agent, x: Int, y: Int): Unit = agentsToShapesAssociations(agent).relocate(x, y)

  def removeAgent(agent: Agent): Unit = {
    trash += agentsToShapesAssociations.get(agent).get
    agentsToShapesAssociations -= agent
  }

  def emptyTrash(): Unit = trash.clear

}

object AgentsShapes {

  def apply(): AgentsShapes = new AgentsShapes

}
