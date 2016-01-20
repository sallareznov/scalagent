package fil.iagl.idl.scalagent.base

import scala.util.Random

/**
  * An agent is an entity capable of acting in an environment [[fil.iagl.idl.scalagent.base.Environment]]
  * and communicating with other agents.
  *
  */
trait Agent {

  var position = Position(0, 0)
  val choices = List(-1, 0, 1)
  var stepX = 0
  var stepY = 0
  getADirection()

  def getADirection(): Unit = {
    stepX = choices(Random.nextInt(3))
    stepY = choices(Random.nextInt(3))
    do {
      stepX = choices(Random.nextInt(3))
      stepY = choices(Random.nextInt(3))
    } while (stepX == 0 && stepY == 0)
  }

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    *
    * @param environment the environment
    */
  def doIt(environment: Environment): Unit

  def getNextPosition(environment: Environment): Position

}
