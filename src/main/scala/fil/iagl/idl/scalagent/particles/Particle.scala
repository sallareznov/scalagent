package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.{Position, Environment, Agent}

/**
  * A particle is an agent which is a particle that can bounce.
  * The particle moves to a randomly chosen cell within the Moore neighborhood
  * (the eight surrounding cells in the environment) if the cell is free. Otherwise
  * (i.e. the cell is already taken by another ball), the agent moves back to the opposite direction.
  * @param position the coordinates of the agent in its surrounding environment
  */
class Particle(position: Position) extends Agent(position) {

  override def doIt(environment: Environment) : Unit = {
    // TODO
    println("doIt()")
  }

}

/**
  * Object companion
  */
object Particle {

  def apply(position: Position): Unit = {
    new Particle(position)
  }

}
