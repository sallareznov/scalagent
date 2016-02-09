package fil.iagl.idl.scalagent.hunt.model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._
import fil.iagl.idl.scalagent.core.model.{Model, Environment}
import fil.iagl.idl.scalagent.core.util.{Position, MooreNeighborhood}
import fil.iagl.idl.scalagent.hunt.util.DijkstraPathFinder

import scala.collection.mutable.ListBuffer
import scala.util.Random

class HuntModel(val nbHunters: Int, val nbObstacles: Int, envHeight: Int, envWidth: Int, agentSize: Double) extends Model {

  val environment = Environment(envWidth, envHeight)
  val alreadyTakenPositions = ListBuffer[Position]()
  val dijkstraPathFinder = DijkstraPathFinder()
  val prey = Prey(environment)

  override def init(): Unit = {
    for (i <- 0 until nbHunters) {
      val hunter = Predator(environment, dijkstraPathFinder)
      hunter.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
      alreadyTakenPositions += hunter.position
      val hunterShape = new Circle(agentSize, Color.RED)
      hunterShape.relocate(hunter.position.x * 5, hunter.position.y * 5)
      agentsShapes.linkAgentToShape(hunter, hunterShape)
      environment.mark(hunter.position.x, hunter.position.y, hunter)
      agents += hunter
    }

    for (i <- 0 until nbObstacles) {
      val obstacle = Obstacle(environment)
      obstacle.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
      alreadyTakenPositions += obstacle.position
      val obstacleShape = new Circle(agentSize, Color.ROSYBROWN)
      obstacleShape.relocate(obstacle.position.x * 5, obstacle.position.y * 5)
      agentsShapes.linkAgentToShape(obstacle, obstacleShape)
      environment.mark(obstacle.position.x, obstacle.position.y, obstacle)
    }

    prey.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
    alreadyTakenPositions += prey.position
    val preyShape = new Circle(agentSize, Color.BLUE)
    preyShape.relocate(prey.position.x * 5, prey.position.y * 5)
    agentsShapes.linkAgentToShape(prey, preyShape)
    environment.mark(prey.position.x, prey.position.y, prey)
    agents += prey
  }


  override def run(): Unit = {
    dijkstraPathFinder.updateDijkstraDistances(environment, prey.position)
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(new MooreNeighborhood(), agentsShapes)
      environment.unmark(agentOldPosition.x, agentOldPosition.y)
      environment.mark(agent.position.x, agent.position.y, agent)
    })
  }
}
