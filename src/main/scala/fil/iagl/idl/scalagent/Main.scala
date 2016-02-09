package fil.iagl.idl.scalagent

import fil.iagl.idl.scalagent.hunt.{HuntView, HuntCommand}
import fil.iagl.idl.scalagent.particles.{ParticlesCommand, ParticlesView}
import fil.iagl.idl.scalagent.wator.{WatorView, WatorCommand}
import org.backuity.clist.Cli

/**
  * Main class. Launches the command corresponding to the user's request
  * `particles` : launches the particles application
  * `wator` : launches the wator application
  * `hunt` : launches the hunt application
  */
object Main {

  def main (args: Array[String]): Unit = {
    Cli.parse(args).withProgramName("sma").withCommands(ParticlesCommand, WatorCommand, HuntCommand) match {
      case Some(ParticlesCommand) => ParticlesView.main(args)
      case Some(WatorCommand) => WatorView.main(args)
      case Some(HuntCommand) => HuntView.main(args)
      case _ => ()
    }
  }

}
