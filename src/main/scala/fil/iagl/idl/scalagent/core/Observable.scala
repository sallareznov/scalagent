package fil.iagl.idl.scalagent.core

import scala.collection.mutable.ListBuffer

abstract class Observable {

  val observers = new ListBuffer[Observer]

  def addObserver(observer: Observer): Unit = (observers += observer)

  def notifyObservers(agents: Array[Agent]): Unit = observers.foreach(_.update(agents))

}
