package fil.iagl.idl.scalagent.hunt

import fil.iagl.idl.scalagent.core._

class Prey(environment: Environment) extends Agent {
  agentType = AgentType.PREY

  override def doIt(agentsShapes: AgentsShapes): Unit = {
    println("Running away!")
  }
}

object Prey {

  def apply(environment: Environment) = new Prey(environment)

}