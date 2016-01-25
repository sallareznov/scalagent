package fil.iagl.idl.scalagent.wator

import fil.iagl.idl.scalagent.core.{AgentsShapes, Position, Environment, Agent}

class Shark extends Agent {
  var starvationCounter: Int = 0

  override def doIt(environment: Environment): Unit = {

    if(tunaIsNear(environment)){
      eatTuna()
      starvationCounter=0
    }
    else
      {
        starvationCounter+=1
        if(!starvation())
          move(environment)
      }

  }

  def move(environment: Environment): Unit = {
    val newX = if ((position.x + stepX) >= 0) position.x + stepX else (position.x + stepX) + environment.width
    val newY = if ((position.y + stepY) >= 0) position.y + stepY else (position.y + stepY) + environment.height
    position = Position(newX % environment.width, newY % environment.height)
    AgentsShapes.relocateShape(this, position)
  }

  def starvation(): Boolean = {
    false
  }

  def eatTuna():Unit={

  }

  def tunaIsNear(environment: Environment): Boolean={
    environment.isTuna(position)
  }

}

object Shark {

  def apply() = new Shark()

}
