package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.{Position, Environment, Agent}

/**
  * A particle is an agent which is a particle that can bounce.
  * The particle moves to a randomly chosen cell within the Moore neighborhood
  * (the eight surrounding cells in the environment) if the cell is free. Otherwise
  * (i.e. the cell is already taken by another ball), the agent moves back to the opposite direction.
  * @param position the coordinates of the agent in its surrounding environment
  */
class Particle(position: Position, toroidal: Boolean) extends Agent(position, toroidal) {

  override def doIt(environment: Environment): Position = {
    // TODO toroidal
    println("doIt()")
//    var newPotentialX = (position.x + stepX) % environment.size
//    var newPotentialY = (position.y + stepY) % environment.size
//    if (environment.takenCells(newPotentialX)(newPotentialY)) {
      getNextPosition(environment)
 //   }
   // Position(newPotentialX, newPotentialY)
  }

  override def getNextPosition(environment: Environment): Position = {
    if (toroidal) {
      var x = if ((position.x + stepX) >= 0) (position.x + stepX) else (position.x + stepX) + environment.size
      var y = if ((position.y + stepY) >= 0) (position.y + stepY) else (position.y + stepY) + environment.size
      Position(x % environment.size, y % environment.size)
    }


    else
      Position((environment.size - position.x - stepX) % environment.size, 0)
  }

}

/**
  * Object companion
  */
object Particle {

  def apply(position: Position, toroidal: Boolean) = new Particle(position, toroidal)

}
