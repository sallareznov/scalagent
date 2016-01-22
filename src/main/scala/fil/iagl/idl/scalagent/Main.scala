package fil.iagl.idl.scalagent

import fil.iagl.idl.scalagent.particles.ParticlesMainCommand
import fil.iagl.idl.scalagent.wator.WatorMainCommand
import org.backuity.clist.Cli

object Main {

  def main (args: Array[String]): Unit = {
    Cli.parse(args).withProgramName("sma").withCommands(ParticlesMainCommand, WatorMainCommand) match {
      case Some(ParticlesMainCommand) => ParticlesMainCommand.handleCommand(args)
      case Some(WatorMainCommand) => WatorMainCommand.handleCommand(args)
      case None => ()
    }
  }

}
