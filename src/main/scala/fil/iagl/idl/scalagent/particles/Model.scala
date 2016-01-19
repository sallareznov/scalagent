package fil.iagl.idl.scalagent.particles
import fil.iagl.idl.scalagent.base.{Observable, Position, Environment, Agent}

import scala.util.Random

/**
  * The model, referencing the environment and the agents inside. The model is the
  * [[java.util.Observable]] of a [[fil.iagl.idl.scalagent.particles.View]]; therefore,
  * the latter can update itself after a lap (a lap is completed when all agents have computed
  * their next move).
  */
class Model(var nbParticles: Int,
            var envSize: Int,
            var agentSize: Int,
            var speed: Int,
            var toroidal: Boolean,
            var visibility: Boolean,
            var equity: Boolean
            ) extends Observable {

  var agents = new Array[Agent](nbParticles)
  for (i <- 0 until agents.length) {
    agents(i) = Particle(Position(Random.nextInt(envSize), Random.nextInt(envSize)))
  }
  if (!equity)
    agents = Random.shuffle(agents.toList).toArray

  val environment = Environment(envSize)

  /**
    * runs the model (simulates the agents' moves)
    * @param nbOfLaps the number of laps to be completed
    */
  def run(nbOfLaps: Int): Unit = {
    println(agents)
    agents.foreach(agent => {
      println("before : " + agent.position)
      agent.doIt(environment)
      println("after : " + agent.position)
    })
    notifyObservers()
    Thread.sleep(speed)
  }

}

object Model {

  def apply(nbParticles: Int,
            envSize: Int,
            agentSize: Int,
            speed: Int,
            toroidal: Boolean,
            visibility: Boolean,
            equity: Boolean) = new Model(nbParticles, envSize, agentSize, speed, toroidal, visibility, equity)

}
