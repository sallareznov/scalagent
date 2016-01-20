package fil.iagl.idl.scalagent.particles

object Main extends App {

  val command = new ParticlesMainCommand()
  command.handleCommand(args)
  command.handleCommand(args)
  val model = Model(command.nbParticles, command.envSize, command.speed, command.toroidal, command.equity)
  val view = new View()
  model.addObserver(view)
  view.test()
  model.run()
}
