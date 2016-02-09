package fil.iagl.idl.scalagent.core.util

import javafx.scene.shape.Shape

import fil.iagl.idl.scalagent.core.model.Agent

import scala.collection.mutable

/**
  * The graphical representations of the agents
  */
class AgentsShapes {

  /**
    * associations representing agents and their corresponding shapes
    */
  val agentsToShapesAssociations = mutable.Map[Agent, Shape]()

  /**
    * the shapes to delete
    */
  val shapesToDelete = mutable.HashSet [Shape]()

  /**
    * links an agent to a shape
    * @param agent the agent to add
    * @param shape the shape to add
    */
  def linkAgentToShape(agent: Agent, shape: Shape): Unit = agentsToShapesAssociations += (agent -> shape)

  /**
    * moves the position of the shape representing the agent
    * @param agent the agent
    * @param x the new abscissa of the shape
    * @param y the new ordinate of the shape
    */
  def relocateShape(agent: Agent, x: Int, y: Int): Unit = agentsToShapesAssociations(agent).relocate(x, y)

  /**
    * remove an agent from the shapes
    * @param agent the agent to remove
    */
  def removeAgent(agent: Agent): Unit = {
    shapesToDelete += agentsToShapesAssociations.get(agent).get
    agentsToShapesAssociations -= agent
  }

  /**
    * deletes the shapes to delete
    */
  def deleteShapesToDelete(): Unit = shapesToDelete.clear

}

/**
  * Companion object
  */
object AgentsShapes {

  def apply(): AgentsShapes = new AgentsShapes

}
