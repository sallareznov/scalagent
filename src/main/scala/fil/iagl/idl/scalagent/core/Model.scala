package fil.iagl.idl.scalagent.core

import scala.collection.mutable

trait Model {

  var agents = mutable.HashSet[Agent]()
  val agentsShapes = AgentsShapes()

  def run(): Unit

}
