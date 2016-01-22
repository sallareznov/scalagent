package fil.iagl.idl.scalagent.particles

import javafx.scene.shape.Circle

import fil.iagl.idl.scalagent.core._

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

  var agents = new Array[Agent](nbParticles)
  val alreadyTakenPositions = ListBuffer[Position]()

  for (i <- agents.indices) {
    agents(i) = Particle(toroidal)
    agents(i).position = Position(Random.nextInt(envWidth), Random.nextInt(envHeight))
    alreadyTakenPositions += agents(i).position
    val agentPosition = agents(i).position
    agents(i).shape = Some(new Circle(agentSize, Colors.randomColor()))
    agents(i).shape.get.relocate(agents(i).position.x, agents(i).position.y)
    environment.mark(agentPosition)
  }
  if (equity)
    agents = Random.shuffle(agents.toList).toArray

  /**
    * runs the model (simulates the agents' moves)
    */
  override def run(): Unit = {
    agents.foreach(agent => {
      val agentOldPosition = agent.position
      agent.doIt(environment)
      environment.unmark(agentOldPosition)
      environment.mark(agent.position)
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
