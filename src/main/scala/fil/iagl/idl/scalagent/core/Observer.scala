package fil.iagl.idl.scalagent.core

trait Observer {

  def update(agents: Array[Agent]): Unit

}
