package fil.iagl.idl.scalagent.particles

import fil.iagl.idl.scalagent.base.Observer

/**
  * The view is the interface rendered to the user. The view will represent
  * the environment and the agents inside. The view is an [[java.util.Observer]]
  * to the [[Model]].
  */
class View extends Observer {

  override def update(): Unit = println("update()")

}
