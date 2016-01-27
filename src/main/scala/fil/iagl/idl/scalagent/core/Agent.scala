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
  var agentType: AgentType.Type = AgentType.NO_TYPE
  var isVisited = false

  /**
    * Performs an action, depending on the environment (mainly the position of other agents)
    *
    */
  def doIt(): Unit

}
