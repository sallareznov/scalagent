package fil.iagl.idl.scalagent.hunt

import javafx.scene.paint.Color
import javafx.scene.shape.{Rectangle, Circle}

import fil.iagl.idl.scalagent.core._

import scala.collection.mutable.ListBuffer
import scala.util.Random

class HuntModel(val nbHunters: Int, val nbObstacles: Int, envHeight: Int, envWidth: Int, agentSize: Double) extends Model {

  val environment = Environment(envWidth, envHeight)
  val alreadyTakenPositions = ListBuffer[Position]()
  val dijkstraPathFinder = DijkstraPathFinder()

  for (i <- 0 until nbHunters) {
    val hunter = Predator(environment, dijkstraPathFinder)
    hunter.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
    // TODO taken positions
    alreadyTakenPositions += hunter.position
    val hunterShape = new Circle(agentSize, Color.RED)
    hunterShape.relocate(hunter.position.x, hunter.position.y)
    agentsShapes.linkAgentToShape(hunter, hunterShape)
    environment.mark(hunter.position.x, hunter.position.y, hunter)
    agents += hunter
  }

  for (i <- 0 until nbObstacles) {
    val obstacle = Obstacle(environment)
    obstacle.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
    // TODO taken positions
    alreadyTakenPositions += obstacle.position
    val obstacleShape = new Rectangle(agentSize, agentSize, Color.ROSYBROWN)
    obstacleShape.relocate(obstacle.position.x, obstacle.position.y)
    agentsShapes.linkAgentToShape(obstacle, obstacleShape)
    environment.mark(obstacle.position.x, obstacle.position.y, obstacle)
    //agents += obstacle
  }

  val prey = Prey(environment)
  prey.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
  // TODO taken positions
  alreadyTakenPositions += prey.position
  val preyShape = new Circle(agentSize, Color.BLUE)
  preyShape.relocate(prey.position.x, prey.position.y)
  agentsShapes.linkAgentToShape(prey, preyShape)
  environment.mark(prey.position.x, prey.position.y, prey)
  agents += prey

  override def run(): Unit = {
    dijkstraPathFinder.updateDijkstraDistances(environment, prey.position)
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(agentsShapes)
      environment.unmark(agentOldPosition.x, agentOldPosition.y)
      environment.mark(agent.position.x, agent.position.y, agent)
    })
  }
}
