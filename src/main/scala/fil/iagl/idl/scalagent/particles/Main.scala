package fil.iagl.idl.scalagent.particles

object Main extends App {

  val command = new ParticlesMainCommand()
  command.handleCommand(args)
  val model = Model(command.nbParticles, command.envSize, command.agentSize, command.speed, command.toroidal, command.equity)
  val view = new ViewSwing(command.envSize, command.agentSize)
  model.addObserver(view)
  model.run

}
