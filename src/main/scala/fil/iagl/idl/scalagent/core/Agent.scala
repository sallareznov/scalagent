package fil.iagl.idl.scalagent.core

import javafx.scene.shape.Shape

import scala.util.Random

/**
  * An agent is an entity capable of acting in an environment [[fil.iagl.idl.scalagent.core.Environment]]
  * and communicating with other agents.
  *
  */
trait Agent {

  var position = Position(0, 0)
  val choices = List(-1, 0, 1)

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    *
    */
  def doIt(): Unit

  def nextPosition(environment: Environment, wantedType: AgentType.Type): Option[Position] = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))

    list1.foreach(i => list2.foreach(j => {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val newPosition = Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height)
      if (environment.getType(newPosition.x, newPosition.y) == wantedType) {
        return Some(newPosition)
      }
    }))
    None
  }



}
