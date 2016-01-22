package fil.iagl.idl.scalagent

import fil.iagl.idl.scalagent.particles.{ParticlesCommand, ParticlesView}
import fil.iagl.idl.scalagent.wator.{WatorView, WatorCommand}
import org.backuity.clist.Cli

object Main {

  def main (args: Array[String]): Unit = {
    Cli.parse(args).withProgramName("sma").withCommands(ParticlesCommand, WatorCommand) match {
      case Some(ParticlesCommand) => ParticlesView.main(args)
      case Some(WatorCommand) => WatorView.main(args)
      case _ => ()
    }
  }

}
