package fil.iagl.idl.scalagent.base

import org.backuity.clist.{Cli, Command}

abstract class CommandWithHandling(override val description: String) extends Command(description) {

  def handleCommand(commandLineArgumentsHandlingManager: CommandLineArgumentsHandlingManager, args: Array[String]) {
    Cli.parse(args).withCommand(CommandWithHandling.this) {
      case _ => options.foreach(option => commandLineArgumentsHandlingManager.handleCommand(option))
    }
  }

}
