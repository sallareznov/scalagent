package fil.iagl.idl.scalagent.particles.model

import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._
import fil.iagl.idl.scalagent.core.model.{Model, Environment}
import fil.iagl.idl.scalagent.core.util.{Position, MooreNeighborhood}
import fil.iagl.idl.scalagent.particles.util.ParticlesRandomColorGenerator

import scala.util.Random

/**
  * The model of the particles environment in a MVC paradigm
  * @constructor constructs a new ParticlesModel with `nbParticles`, `envWidth`, `envHeight`, `agentSize`, `speed`, `toroidal` and `equity`
  * @param nbParticles the number of particles in the environment
  * @param envWidth the width of the environment
  * @param envHeight the height of the environment
  * @param agentSize the initial number of tunas in the environment
  * @param toroidal `true` if the environment is toroidal
  * @param equity `true` the order of the agents' deciding is random
  */
class ParticlesModel(val nbParticles: Int,
                     val envWidth: Int,
                     val envHeight: Int,
                     val agentSize: Double,
                     val toroidal: Boolean,
                     val equity: Boolean
                    ) extends Model {

  val environment = Environment(envWidth, envHeight)

  override def init(): Unit = {
    for (i <- 0 until nbParticles) {
      val particle = Particle(toroidal, environment)
      particle.position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
      val particleShape = new Circle(agentSize, ParticlesRandomColorGenerator.randomColor())
      particleShape.relocate(particle.position.x * 5, particle.position.y * 5)
      agentsShapes.linkAgentToShape(particle, particleShape)
      environment.mark(particle.position.x, particle.position.y, particle)
      agents += particle
    }

    if (equity)
      agents = Random.shuffle(agents)
  }


  /**
    * runs the model (simulates the agents' moves)
    */
  override def run(): Unit = {
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(new MooreNeighborhood(), agentsShapes)
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
