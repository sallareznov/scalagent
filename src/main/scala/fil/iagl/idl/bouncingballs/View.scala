package fil.iagl.idl.bouncingballs

import java.util.{Observable, Observer}

/**
  * The view is the interface rendered to the user. The view will represent
  * the environment and the agents inside. The view is an [[java.util.Observer]]
  * to the [[fil.iagl.idl.bouncingballs.Model]].
  */
class View extends Observer {

  override def update(o: Observable, arg: scala.Any): Unit = {

  }

}
