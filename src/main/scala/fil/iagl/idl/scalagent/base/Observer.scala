package fil.iagl.idl.scalagent.base

trait Observer {

  def update(agents: Array[Agent]): Unit

}
