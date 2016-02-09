package fil.iagl.idl.scalagent.core.model

/**
  * The environment
  */
class Environment(val width: Int, val height: Int) {

  /**
    * the agents of the environment
    */
  var agents = Array.ofDim[Option[Agent]](width, height)

  for (i <- 0 until width; j <- 0 until height) {
    agents(i)(j) = None
  }

  /**
    * returns the agent at the position (`x`, `y`)
    * @param x the abscissa of the agent
    * @param y the ordinate of the agent
    * @return the agent at the position (`x`, `y`)
    */
  def getAgent(x: Int, y: Int): Option[Agent] = agents(x)(y)

  /**
    * returns `true` if the agent at the position (`x`, `y`) is free
    * @param x the abscissa of the agent
    * @param y the ordinate of the agent
    * @return `true` if the agent at the position (`x`, `y`) is free
    */
  def isFree(x: Int, y:Int): Boolean = agents(x)(y).isEmpty

  /**
    * puts the agent at the position (`x`, `y`)
    * @param x the abscissa of the agent to mark
    * @param y the ordinate of the agent to mark
    * @param agent the agent to mark
    */
  def mark(x: Int, y: Int, agent: Agent): Unit = agents(x)(y) = Some(agent)

  /**
    * removes the agent from the position (`x`, `y`)
    * @param x the at of the agent to unmark
    * @param y the ordinate of the agent to unmark
    */
  def unmark(x: Int, y: Int): Unit = agents(x)(y) = None

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