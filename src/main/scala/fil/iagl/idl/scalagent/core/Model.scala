package fil.iagl.idl.scalagent.core

trait Model {

  var environment: Environment
  var agents: Array[Agent]

  def run(): Unit

}
