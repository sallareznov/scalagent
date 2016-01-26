package fil.iagl.idl.scalagent.core

/**
  * The environment.
  */
class Environment(val width: Int, val height: Int) {

  var agentsTypes = Array.ofDim[AgentType.Type](width, height)

  // TODO better way (one line) ?
  for (i <- 0 until width; j <- 0 until height) {
    agentsTypes(i)(j) = AgentType.NO_TYPE
  }

  def getType(x: Int, y: Int): AgentType.Type = agentsTypes(x)(y)

  def isFree(x: Int, y:Int): Boolean = agentsTypes(x)(y) == AgentType.NO_TYPE

  def mark(x: Int, y: Int, agentType: AgentType.Type): Unit = agentsTypes(x)(y) = agentType

  def unmark(x: Int, y: Int): Unit = agentsTypes(x)(y) = AgentType.NO_TYPE

}

/**
  * Object companion
  */
object Environment {

  /**
    * initializes a new environment
    */
  def apply(width: Int, height: Int) = new Environment(width, height)


}