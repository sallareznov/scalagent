package fil.iagl.idl.magentscala.particles

import fil.iagl.idl.magentscala.base.{Position, Environment, Agent}

/**
  * A bouncing ball is an agent which is a ball that can bounce.
  * @param position the coordinates of the agent in its surrounding environment
  */
class Particle(position: Position) extends Agent(position) {

  override def doIt(environment: Environment) : Unit = {
    // TODO
    println("doIt()")
  }

}

/**
  * Companion object
  */
object Particle {

  def apply(position: Position): Unit = {
    new Particle(position)
  }

}
