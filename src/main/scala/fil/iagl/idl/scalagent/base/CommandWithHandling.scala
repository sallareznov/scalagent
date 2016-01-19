package fil.iagl.idl.scalagent.base

import org.backuity.clist.Command

abstract class CommandWithHandling(override val description: String) extends Command(description) {

  def handleCommand(args: Array[String])

}
