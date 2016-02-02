package fil.iagl.idl.scalagent.hunt

import fil.iagl.idl.scalagent.core._

class Obstacle(environment: Environment) extends Agent{
  agentType = AgentType.OBSTACLE

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    *
    */
  override def doIt(agentsShapes: AgentsShapes): Unit = {}
}

object Obstacle {

  def apply(environment: Environment) = new Obstacle(environment)

}

