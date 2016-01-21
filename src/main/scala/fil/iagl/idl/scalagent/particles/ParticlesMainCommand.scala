package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.core.CommandWithHandling
import org.backuity.clist._

class ParticlesMainCommand() extends CommandWithHandling(description = "simulates a bubble chamber using a multi-agent approach") {

  var nbParticles = opt[Int](name = "nbParticles", default = 10000, description = "the number of particles in the room")
  var envWidth = opt[Int](name = "envWidth", default = 0, description = "the width of the grid representing the environment")
  var envHeight = opt[Int](name = "envHeight", default = 0, description = "the height of the grid representing the environment")
  var agentSize = opt[Double](name = "agentSize", default = 2.5, description = "the size oœf the agent (in pixels)")
  var speed = opt[Int](name = "speed", default = 10, description = "the speed of the game (i.e. the number of milliseconds per lap")
  var toroidal = opt[Boolean](name = "toroidal", description = "if activated, the grid will be toroidal")
  var visibility = opt[Boolean](name = "visibility", description = "if activated, the plots of the grid will be visible")
  var equity = opt[Boolean](name = "equity", description = "if activated, there will be no ordering in the speaking of the agents (i.e. random)")
  // TODO seed (???)

  override def handleCommand(args: Array[String]) {
    Cli.parse(args).withCommand(ParticlesMainCommand.this) {
      case opt => {}
    }
  }

}

