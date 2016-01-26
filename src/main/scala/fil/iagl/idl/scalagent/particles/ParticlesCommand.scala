package fil.iagl.idl.scalagent.particles

import org.backuity.clist._

object ParticlesCommand extends Command(name = "particles", description = "simulates a bubble chamber using a multi-agent approach") {

  // -> command arguments
  var nbParticles = opt[Int](name = "nbParticles", default = 10000, description = "the number of particles in the room [default = 10000]")
  var envWidth = opt[Int](name = "envWidth", default = 0, description = "the width of the grid representing the environment [default = the width of the screen]")
  var envHeight = opt[Int](name = "envHeight", default = 0, description = "the height of the grid representing the environment [default = the height of the screen]")
  var agentSize = opt[Double](name = "agentSize", default = 2.5, description = "the size of the agent (i.e the radius in pixels of the circles representing the particles) [default = 2.5]")
  var speed = opt[Int](name = "speed", default = 10, description = "the speed of the game (i.e. the number of milliseconds per lap [default = 10]")
  var toroidal = opt[Boolean](name = "toroidal", description = "if activated, the grid will be toroidal [default = false]")
  var visible = opt[Boolean](name = "visible", description = "if activated, the lines of the grid will be visible [default = false]")
  var equity = opt[Boolean](name = "equity", description = "if activated, there will be no ordering in the speaking of the agents (i.e. random) [default = false]")
  // TODO seed (???)

}
