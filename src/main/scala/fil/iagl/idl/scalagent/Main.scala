package fil.iagl.idl.scalagent

import fil.iagl.idl.scalagent.particles.{Model, ParticlesMainCommand, View}

object Main {

  def main (args: Array[String]) {
    val command = new ParticlesMainCommand()
    command.handleCommand(args)
    val model = new Model(command.nbParticles, command.envSize, command.agentSize, command.speed, command.toroidal, command.equity)
    val view = new View()
    /*view.model = Seq(model).lift(0)
    view.model.get.addObserver(view)
    view.main(args)*/

  }

}
