package fil.iagl.idl.scalagent.wator

import fil.iagl.idl.scalagent.core.{AgentsShapes, Position, Environment, Agent}

import scala.util.Random
import scalafx.scene.shape

class Tuna(val breed: Int) extends Agent {

  var currentBreed = 0
  randomDirection()

  override def doIt(environment: Environment): Unit = {
    val nextPotentialPosition = nextPosition(environment)
    nextPotentialPosition match {
      case Some(x) => {
        val littleTuna = Tuna(breed)
        littleTuna.position = Position(position.x, position.y)
        val littleTunaShape =
      }
    }
    if (currentBreed == breed) {
      val littleTuna =
      // TODO reproduction
    }
    /*val newX = if ((position.x + stepX) >= 0) (position.x + stepX) else (position.x + stepX) + environment.width
    val newY = if ((position.y + stepY) >= 0) (position.y + stepY) else (position.y + stepY) + environment.height
    position = Position(newX % environment.width, newY % environment.height)
    AgentsShapes.relocateShape(this, position)*/

    currentBreed += 1
    AgentsShapes.relocateShape(this, position)
  }

  def nextPosition(environment: Environment): Option[Position] = {
    // TODO find a more functional way to write these loops
    for (i <- -1 to 1; j <- -1 to 1) {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      if (!environment.takenCells(toroidalNextPotentialAbscissa % environment.width)(toroidalNextPotentialOrdinate % environment.height)) {
        Some(Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height))
      }
    }
    None
  }
}

object Tuna {

  def apply(breed: Int) = new Tuna(breed)

}