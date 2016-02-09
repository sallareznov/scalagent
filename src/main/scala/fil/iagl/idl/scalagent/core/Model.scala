package fil.iagl.idl.scalagent.core

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
