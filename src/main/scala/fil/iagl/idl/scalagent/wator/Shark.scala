package fil.iagl.idl.scalagent.wator

import fil.iagl.idl.scalagent.core.{Position, Environment, Agent}

class Shark extends Agent {

  override def doIt(environment: Environment): Unit = {
    val newX = if ((position.x + stepX) >= 0) (position.x + stepX) else (position.x + stepX) + environment.width
    val newY = if ((position.y + stepY) >= 0) (position.y + stepY) else (position.y + stepY) + environment.height
    position = Position(newX % environment.width, newY % environment.height)
    shape.get.relocate(position.x, position.y)
  }

}

object Shark {

  def apply() = new Shark()

}
