package fil.iagl.idl.scalagent.core.model

import fil.iagl.idl.scalagent.core.util.AgentsShapes

import scala.collection.mutable

/**
  * The model in the MVC paradigm
  */
trait Model {

  /**
    * the agents of the model
    */
  var agents = mutable.HashSet[Agent]()

  /**
    * the shapes of the model
    */
  val agentsShapes = AgentsShapes()

  /**
    * initializes the model by creating the environment and the agents
    */
  def init(): Unit

  /**
    * runs the model (simulates a cycle)
    */
  def run(): Unit

}
