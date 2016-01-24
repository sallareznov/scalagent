package fil.iagl.idl.scalagent.core

import scala.collection.mutable.ListBuffer

trait Model {

  var environment: Environment
  var agents: ListBuffer[Agent]

  def run(): Unit

}
