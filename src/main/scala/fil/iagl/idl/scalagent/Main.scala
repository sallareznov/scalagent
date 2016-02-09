package fil.iagl.idl.scalagent

import fil.iagl.idl.scalagent.hunt.util.HuntCommandOptions
import fil.iagl.idl.scalagent.hunt.view.HuntView
import fil.iagl.idl.scalagent.particles.util.ParticlesCommandOptions
import fil.iagl.idl.scalagent.particles.view.ParticlesView
import fil.iagl.idl.scalagent.wator.util.WatorCommandOptions
import fil.iagl.idl.scalagent.wator.view.WatorView
import org.backuity.clist.Cli

/**
  * Main class. Launches the command corresponding to the user's request
  * `particles` : launches the particles application
  * `wator` : launches the wator application
  * `hunt` : launches the hunt application
  */
object Main {

  def main (args: Array[String]): Unit = {
    Cli.parse(args).withProgramName("sma").withCommands(ParticlesCommandOptions, WatorCommandOptions, HuntCommandOptions) match {
      case Some(ParticlesCommandOptions) => ParticlesView.main(args)
      case Some(WatorCommandOptions) => WatorView.main(args)
      case Some(HuntCommandOptions) => HuntView.main(args)
      case _ => ()
    }
  }

}
