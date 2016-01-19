package fil.iagl.idl.scalagent.base

import scala.collection.mutable.ListBuffer

abstract class Observable {

  val observers = new ListBuffer[Observer]

  def addObserver(observer: Observer): Unit = (observers += observer)

  def notifyObservers(): Unit = observers.foreach(_.update())

}
