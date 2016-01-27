package fil.iagl.idl.scalagent.particles

import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random

class ParticlesModel(val nbParticles: Int,
                     val envWidth: Int,
                     val envHeight: Int,
                     val agentSize: Double,
                     val speed: Int,
                     val toroidal: Boolean,
                     val equity: Boolean
                    ) extends Model {

  val environment = Environment(envWidth, envHeight)
  override var agents = mutable.HashSet[Agent]()

  val alreadyTakenPositions = ListBuffer[Position]()

  for (i <- 0 until nbParticles) {
    val particle = Particle(toroidal, environment)
    particle.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
    // TODO taken positions
    alreadyTakenPositions += particle.position
    val particleShape = new Circle(agentSize, ParticlesColorsGenerator.randomColor())
    particleShape.relocate(particle.position.x, particle.position.y)
    AgentsShapes.linkAgentToShape(particle, particleShape)
    environment.mark(particle.position.x, particle.position.y, particle)
    agents += particle
  }

  //environment.agentsTypes.foreach(_.foreach(println))

  if (equity)
    agents = Random.shuffle(agents)

  /**
    * runs the model (simulates the agents' moves)
    */
  override def run(): Unit = {
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt()
      environment.unmark(agentOldPosition.x, agentOldPosition.y)
      environment.mark(agent.position.x, agent.position.y, agent)
    })
  }

}

object ParticlesModel {

  def apply(nbParticles: Int,
            envWidth: Int,
            envHeight: Int,
            agentSize: Double,
            speed: Int,
            toroidal: Boolean,
            equity: Boolean) = new ParticlesModel(nbParticles, envWidth, envHeight, agentSize, speed, toroidal, equity)

}
