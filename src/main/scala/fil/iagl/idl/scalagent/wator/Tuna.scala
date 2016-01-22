package fil.iagl.idl.scalagent.wator

import fil.iagl.idl.scalagent.core.{Position, Environment, Agent}

import scala.util.Random

class Tuna extends Agent {

  randomDirection()

  override def doIt(environment: Environment): Unit = {
    var randomPosition = Position(Random.nextInt(environment.width), Random.nextInt(environment.height))
    while (environment.takenCells(randomPosition.x)(randomPosition.y)) {
      randomPosition = Position(Random.nextInt(environment.width), Random.nextInt(environment.height))
    }
  }

  override def getNextPosition(environment: Environment): Position = ???
}
