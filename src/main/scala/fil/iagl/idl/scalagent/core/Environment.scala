package fil.iagl.idl.scalagent.core

/**
  * The environment.
  */
class Environment(val width: Int, val height: Int) {

  var agentsTypes = Array.ofDim[Option[Agent]](width, height)

  // TODO better way (one line) ?
  for (i <- 0 until width; j <- 0 until height) {
    agentsTypes(i)(j) = None
  }

  def getAgent(x: Int, y: Int): Option[Agent] = agentsTypes(x)(y)

  def isFree(x: Int, y:Int): Boolean = agentsTypes(x)(y).isEmpty

  def mark(x: Int, y: Int, agent: Agent): Unit = agentsTypes(x)(y) = Some(agent)

  def unmark(x: Int, y: Int): Unit = agentsTypes(x)(y) = None

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