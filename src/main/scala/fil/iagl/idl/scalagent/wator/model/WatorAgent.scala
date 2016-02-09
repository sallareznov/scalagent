package fil.iagl.idl.scalagent.wator.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._
import fil.iagl.idl.scalagent.core.model.{Environment, Agent}
import fil.iagl.idl.scalagent.core.util.{Position, Observable, AgentType, AgentsShapes}
import fil.iagl.idl.scalagent.wator.util.WatorMetricsData

import scala.util.Random

/**
  * An agent in the Wator environment
  * @constructor Create a new agent with a `breed`
  * @param breed the number of cycles the agent must exist before reproducing
  */
abstract class WatorAgent(val breed: Int) extends Agent with Observable {

  /**
    * the number of cycles the agent exists since its birth or its last reproduction
    */
  var breedCounter = 0

  /**
    * the age of the agent (the number of cycles it existed)
    */
  var age = 0

  /**
    * returns a free neighbor position
    * @param environment the environment
    * @return a free neighbor position
    */
  def nextFreePosition(environment: Environment): Option[Position] = {
    val list1 = Random.shuffle((-1).to(1))
    val list2 = Random.shuffle((-1).to(1))

    list1.foreach(i => list2.foreach(j => {
      val toroidalNextPotentialAbscissa = if ((position.x + i) >= 0) position.x + i else (position.x + i) + environment.width
      val toroidalNextPotentialOrdinate = if ((position.y + j) >= 0) position.y + j else (position.y + j) + environment.height
      val newPosition = Position(toroidalNextPotentialAbscissa % environment.width, toroidalNextPotentialOrdinate % environment.height)
      if (environment.isFree(newPosition.x, newPosition.y)) {
        return Some(newPosition)
      }
    }))
    None
  }

  /**
    * moves in the environment and reproduces if possible
    * @param environment environment
    * @param potentialChild the potential child to give birth to, if a reproduction is possible
    * @param color the color of the agent (which will be the color of the child)
    * @param agentsShapes the existing shapes (the shape of the potential child will be added to it)
    */
  def moveAndAimToReproduce(environment: Environment, potentialChild: Agent, color: Color, agentsShapes: AgentsShapes): Unit = {
    if (breedCounter == breed) {
      potentialChild.position = Position(position.x, position.y)
      val childShape = new Circle(2.5, color)
      childShape.relocate(position.x * 4, position.y * 4)
      agentsShapes.linkAgentToShape(potentialChild, childShape)
      environment.mark(potentialChild.position.x, potentialChild.position.y, potentialChild)
      if (agentType == AgentType.TUNA) WatorMetricsData.incrementNTunas() else WatorMetricsData.incrementNSharks()
      breedCounter = 0
    }
    else {
      breedCounter += 1
      environment.unmark(position.x, position.y)
    }
    age += 1
  }

}
