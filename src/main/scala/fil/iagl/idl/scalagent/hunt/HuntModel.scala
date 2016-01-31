package fil.iagl.idl.scalagent.hunt

import javafx.event.EventHandler
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._

import scala.collection.mutable.ListBuffer
import scala.util.Random

class HuntModel(val nbHunters: Int, val nbObstacles: Int, envHeight: Int, envWidth: Int, agentSize: Double) extends Model {


  val environment = Environment(envWidth, envHeight)

  val alreadyTakenPositions = ListBuffer[Position]()

  for (i <- 0 until nbHunters) {
    val hunter = Hunter(environment)
    hunter.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
    // TODO taken positions
    alreadyTakenPositions += hunter.position
    val hunterShape = new Circle(agentSize, Color.BLUE)
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
    val obstacleShape = new Circle(agentSize, Color.ROSYBROWN)
    obstacleShape.relocate(obstacle.position.x, obstacle.position.y)
    agentsShapes.linkAgentToShape(obstacle, obstacleShape)
    environment.mark(obstacle.position.x, obstacle.position.y, obstacle)
    //agents += obstacle
  }

  val prey = Prey(environment)
  prey.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
  // TODO taken positions
  alreadyTakenPositions += prey.position
  val preyShape = new Circle(agentSize, Color.RED)
  preyShape.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler[KeyEvent] {
    override def handle(event: KeyEvent): Unit = {
      println("handling")
      event.getCode match {
        case KeyCode.UP => prey.direction = Direction.NORTH
        case KeyCode.DOWN => prey.direction = Direction.SOUTH
        case KeyCode.RIGHT => prey.direction = Direction.EAST
        case KeyCode.LEFT => prey.direction = Direction.WEST
        case _ =>
      }
    }
  })
  preyShape.relocate(prey.position.x, prey.position.y)
  agentsShapes.linkAgentToShape(prey, preyShape)
  environment.mark(prey.position.x, prey.position.y, prey)
  agents += prey


  override def run(): Unit = {
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(agentsShapes)
      environment.unmark(agentOldPosition.x, agentOldPosition.y)
      environment.mark(agent.position.x, agent.position.y, agent)
    })
  }
}
