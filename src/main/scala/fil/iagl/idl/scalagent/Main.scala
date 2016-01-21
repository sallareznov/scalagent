package fil.iagl.idl.scalagent

import fil.iagl.idl.scalagent.particles.{ParticlesModel, ParticlesMainCommand, ParticlesView}

object Main {

  def main (args: Array[String]) {
    val command = new ParticlesMainCommand()
    command.handleCommand(args)
    val model = new ParticlesModel(command.nbParticles, command.envSize, command.agentSize, command.speed, command.toroidal, command.equity)
    val view = new ParticlesView()
    /*view.model = Seq(model).lift(0)
    view.model.get.addObserver(view)
    view.main(args)*/

  }

}
