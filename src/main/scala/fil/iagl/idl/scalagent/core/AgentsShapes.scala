package fil.iagl.idl.scalagent.core

import javafx.scene.shape.Shape

import scala.collection.mutable

object AgentsShapes {

  val agentsShapes = scala.collection.mutable.Map[Agent, Shape]()
  val trash = mutable.HashSet [Shape]()

  def linkAgentToShape(agent: Agent, shape: Shape): Unit = agentsShapes += (agent -> shape)

  def relocateShape(agent: Agent, x: Int, y: Int): Unit = agentsShapes(agent).relocate(x, y)

  def removeAgent(agent: Agent): Unit = {
    trash += agentsShapes.get(agent).get
    agentsShapes -= agent
  }

  def emptyTrash(): Unit = trash.clear

}
