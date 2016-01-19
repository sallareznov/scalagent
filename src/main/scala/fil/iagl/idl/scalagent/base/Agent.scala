package fil.iagl.idl.scalagent.base

import scala.util.Random

/**
  * An agent is an entity capable of acting in an environment [[fil.iagl.idl.scalagent.base.Environment]]
  * and communicating with other agents.
  * @param position the coordinates of the agent in its surrounding environment
  */
abstract class Agent(val position: Position, val toroidal: Boolean) {

  var stepX = Random.nextInt(2)
  var stepY = Random.nextInt(2)

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    * @param environment the environment
    */
  def doIt(environment: Environment): Position

  def getNextPosition(environment: Environment): Position

}
