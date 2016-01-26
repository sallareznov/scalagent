package fil.iagl.idl.scalagent.core

trait Observer {

  def update(agents: scala.collection.Set[Agent]): Unit

}