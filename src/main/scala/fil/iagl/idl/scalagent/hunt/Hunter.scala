package fil.iagl.idl.scalagent.hunt

import fil.iagl.idl.scalagent.core._

class Hunter(val environment: Environment) extends Agent {
  agentType = AgentType.HUNTER

  override def doIt(agentsShapes: AgentsShapes): Unit = {
    println("hunting..")

  }
}

object Hunter {

  def apply(environment: Environment) = new Hunter(environment)

}