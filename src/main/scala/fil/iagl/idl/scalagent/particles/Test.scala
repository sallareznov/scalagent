package fil.iagl.idl.scalagent.particles

object Test extends App {

    Main.main(Array(""))
    val commandWithHandling = new ParticlesMainCommand()
    commandWithHandling.handleCommand(args)

}
