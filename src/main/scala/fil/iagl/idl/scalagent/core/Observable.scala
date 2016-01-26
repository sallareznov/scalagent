package fil.iagl.idl.scalagent.core

import scala.collection.mutable.ListBuffer

trait Observable {

  val observers = new ListBuffer[Observer]

  def addObserver(observer: Observer): Unit = (observers += observer)

  def notifyObservers(agents: scala.collection.Set[Agent]): Unit = observers.foreach(_.update(agents))

}