package fil.iagl.idl.scalagent.hunt.model

import fil.iagl.idl.scalagent.core._
import fil.iagl.idl.scalagent.core.model.{Environment, Agent}
import fil.iagl.idl.scalagent.core.util.{Neighborhood, AgentType, AgentsShapes}

class Obstacle(environment: Environment) extends Agent{
  agentType = AgentType.OBSTACLE

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    *
    */
  override def doIt(neighborhood: Neighborhood, agentsShapes: AgentsShapes): Unit = {}
}

object Obstacle {

  def apply(environment: Environment) = new Obstacle(environment)

}

