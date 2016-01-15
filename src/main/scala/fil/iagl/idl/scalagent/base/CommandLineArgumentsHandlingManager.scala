package fil.iagl.idl.scalagent.base

import org.backuity.clist.CliOption

class CommandLineArgumentsHandlingManager() {

  var argumentsHandlers = List[CommandLineArgumentHandler]()

  def addCommandHandler(argumentHandler: CommandLineArgumentHandler) : Unit = {
    argumentsHandlers = argumentsHandlers :+ argumentHandler
  }

  def handleCommand(cliOption: CliOption[_]) : Unit = {
    val handlerOption = argumentsHandlers.find(commandHandler => commandHandler.accept(cliOption))
    handlerOption match {
      case Some(handler) => handler.execute(cliOption)
      case None => () // TODO default value functionality
    }
  }

}
