package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.CommandLineArgumentsHandlingManager

object Test {

  def main(args: Array[String]) {
    val commandWithHandling = new ParticlesMainCommand
    val commandLineArgumentsHandlingManager = new CommandLineArgumentsHandlingManager
    commandWithHandling.handleCommand(commandLineArgumentsHandlingManager, args)
  }

}
