package fil.iagl.idl.scalagent.core

trait Model {

  var agents: scala.collection.mutable.HashSet[Agent]

  def run(): Unit

}
