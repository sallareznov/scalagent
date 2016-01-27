package fil.iagl.idl.scalagent.core

import javafx.scene.shape.Shape

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait Observable {

  val observers = new ListBuffer[Observer]

  def addObserver(observer: Observer): Unit = (observers += observer)

  def notifyObservers(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit = observers.foreach(_.update(newAgents, deletedShapes))

}