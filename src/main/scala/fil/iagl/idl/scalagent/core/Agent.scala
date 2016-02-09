package fil.iagl.idl.scalagent.core

/**
  * An agent is an entity capable of acting in an environment [[Environment]]
  * and communicating with other agents.
  *
  */
trait Agent {

  /**
    * the position of the agent
    */
  var position = Position(0, 0)

  val choices = List(-1, 0, 1)

  /**
    * the type of the agent
    */
  var agentType: AgentType.Type = AgentType.NO_TYPE
  var isVisited = false

  /**
    * Performs an action, depending on the given neighborhood and the shapes of the other agents
    * @param neighborhood the neighborhood
    * @param agentsShapes the shapes of the other agents
    */
  def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit

}
