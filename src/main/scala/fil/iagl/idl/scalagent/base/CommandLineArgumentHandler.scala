package fil.iagl.idl.scalagent.base

import org.backuity.clist.CliOption

trait CommandLineArgumentHandler {

  def accept(cliOption: CliOption[_]): Boolean

  def execute(cliOption: CliOption[_]): Unit

}
