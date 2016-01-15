package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.CommandWithHandling
import org.backuity.clist._

class ParticlesMainCommand extends CommandWithHandling(description = "simulates a bubble chamber using a multi-agent approach") {

  var nbParticles = opt[Int](name = "nbParticles", description = "the number of particles in the room")
  var envSize = opt[Int](name = "envSize", description = "the size of the grid representing the environment")
  var agentSize = opt[Int](name = "agentSize", description = "the size of the agent (in pixels)")
  var speed = opt[Int](name = "speed", description = "the speed of the game (i.e. the number of milliseconds per lap")
  var toroidal = opt[Boolean](name = "toroidal", description = "if activated, the grid will be toroidal")
  var visibility = opt[Boolean](name = "visibility", description = "vvvvvvvvvvvvvvvvvvvvv")
  var equity = opt[Boolean](name = "equity", description = "if activated, there will be no ordering in the speaking of the agents (i.e. random)")
  // TODO seed (???)

}

