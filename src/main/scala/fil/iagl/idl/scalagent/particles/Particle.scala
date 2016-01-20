package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.{Position, Environment, Agent}

import scala.util.Random

/**
  * A particle is an agent which is a particle that can bounce.
  * The particle moves to a randomly chosen cell within the Moore neighborhood
  * (the eight surrounding cells in the environment) if the cell is free. Otherwise
  * (i.e. the cell is already taken by another ball), the agent moves back to the opposite direction.
  *
  * @param position the coordinates of the agent in its surrounding environment
  */
class Particle(position: Position, toroidal: Boolean) extends Agent(position, toroidal) {


  override def doIt(environment: Environment): Position = {
    println("doIt()")
    val newPosition = getNextPosition(environment)
    if (positionIsEmpty(newPosition, environment)) {
      newPosition
    } else
      changeDirection(newPosition, environment)
  }

  override def getNextPosition(environment: Environment): Position = {
    if (toroidal) {
      val newX = if ((position.x + stepX) >= 0) (position.x + stepX) else (position.x + stepX) + environment.size
      val newY = if ((position.y + stepY) >= 0) (position.y + stepY) else (position.y + stepY) + environment.size
      Position(newX % environment.size, newY % environment.size)
    }
    else {
      var newX = (position.x + stepX)
      var newY = (position.y + stepY)

      if ((newX < 0) || (newX >= environment.size)) {
        stepX = -stepX
        newX = position.x + stepX
      }

      if ((newY < 0) || (newY >= environment.size)) {
        stepY = -stepY
        newY = position.y + stepY
      }
      Position(newX, newY)
    }
  }

  def positionIsEmpty(newPosition: Position, environment: Environment): Boolean = environment.takenCells(newPosition.x)(newPosition.y)

  def changeDirection(position: Position, environment: Environment): Position = {
    var newPosition = position
    do {
      stepX = Random.nextInt(3) match {
        case 0 => 0;
        case 1 => 1;
        case 2 => -1;
      }
      stepY = Random.nextInt(3) match {
        case 0 => 0;
        case 1 => 1;
        case 2 => -1;
      }
      newPosition = getNextPosition(environment)

    } while (((newPosition.x != position.x) && (newPosition.y != position.y)) && (!positionIsEmpty(position, environment)))
    newPosition
  }


}

/**
  * Object companion
  */
object Particle {

  def apply(position: Position, toroidal: Boolean) = new Particle(position, toroidal)

}
