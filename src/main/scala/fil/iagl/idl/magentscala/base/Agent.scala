package fil.iagl.idl.magentscala.base

import Position

/**
  * An agent is an entity capable of acting in an environment [[fil.iagl.idl.bouncingballs.Environment]]
  * and communicating with other agents. The agent moves to a randomly chosen cell within the Moore neighborhood
  * (the eight surrounding cells in the environment) if the cell is free. Otherwise
  * (i.e. the cell is already taken by another ball), the agent moves back to the opposite direction.
  * @param position the coordinates of the agent in its surrounding environment
  */
abstract class Agent(var position: Position) {


  var stepX = 0
  var stepY = 0

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    * @param environment the environment
    */
  def doIt(environment: Environment) : Unit

}
