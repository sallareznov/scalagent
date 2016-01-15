package fil.iagl.idl.scalagent.particles

import org.backuity.clist._

object ParticlesMainCommand extends Command(description = "simulates a bubble chamber using a multi-agent approach") {

  var nbParticles = opt[Int](description = "the number of particles in the room")
  var envSize = opt[Int](description = "the size of the grid representing the environment")
  var agentSize = opt[Int](description = "the size of the agent (in pixels)")
  var speed = opt[Int](description = "the speed of the game (i.e. the number of milliseconds per lap")
  var toroidal = opt[Boolean](abbrev = "to", description = "if activated, the grid will be toroidal")
  var visibility = opt[Boolean](abbrev = "v", description = "vvvvvvvvvvvvvvvvvvvvv")
  var equity = opt[Boolean](abbrev = "eq", description = "if activated, there will be no ordering in the speaking of the agents (i.e. random)")
  // TODO seed (???)

  def main(args: Array[String]) {
    Cli.parse(args).withCommand(ParticlesMainCommand) {
      case _ => options.foreach(u => println(u.commandAttributeName))
    }
  }

}
