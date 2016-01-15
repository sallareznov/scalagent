package fil.iagl.idl.scalagent.base

/**
  * An agent is an entity capable of acting in an environment [[fil.iagl.idl.scalagent.base.Environment]]
  * and communicating with other agents.
  * @param position the coordinates of the agent in its surrounding environment
  */
abstract class Agent(var position: Position) {

  var stepX = 0
  var stepY = 0

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    * @param environment the environment
    */
  def doIt(environment: Environment): Unit

}
