package fil.iagl.idl.scalagent.particles
import fil.iagl.idl.scalagent.base.{Observable, Position, Environment, Agent}

import scala.util.Random

/**
  * The model, referencing the environment and the agents inside. The model is the
  * [[Observable]] of a [[View]]; therefore,
  * the latter can update itself after a lap (a lap is completed when all agents have computed
  * their next move).
  */
class Model(val nbParticles: Int,
            val envSize: Int,
            val agentSize: Int,
            val speed: Int,
            val toroidal: Boolean,
            val equity: Boolean
            ) extends Observable {

  val environment = Environment(envSize)

  var agents = new Array[Agent](nbParticles)
  for (i <- 0 until agents.length) {
    agents(i) = Particle(Position(Random.nextInt(envSize), Random.nextInt(envSize)), toroidal)
    // TODO check equality
    val agentPosition = agents(i).position
    environment.mark(agentPosition)
  }
  if (equity)
  agents = Random.shuffle(agents.toList).toArray


  /**
    * runs the model (simulates the agents' moves)
    */
  def run(): Unit = {
    while (true) {
      agents.foreach(agent => {
        val agentOldPosition = agent.position
        agent.doIt(environment)
        environment.unmark(agentOldPosition)
        environment.mark(agent.position)
      })
      notifyObservers(environment.takenCells)
      Thread.sleep(speed)
    }
  }

}

object Model {

  def apply(nbParticles: Int,
            envSize: Int,
            agentSize: Int,
            speed: Int,
            toroidal: Boolean,
            equity: Boolean) = new Model(nbParticles, envSize, agentSize, speed, toroidal, equity)

}
