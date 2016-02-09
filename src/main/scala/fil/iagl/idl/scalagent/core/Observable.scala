package fil.iagl.idl.scalagent.core

import javafx.scene.shape.Shape

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * An Observable (c.f. Observer pattern)
  */
trait Observable {

  /**
    * the list of observers
    */
  val observers = new ListBuffer[Observer]

  /**
    * adds an observer to the list of observers
    * @param observer the observer to add
    */
  def addObserver(observer: Observer): Unit = observers += observer

  /**
    * notify the observers of an event
    * @param newAgents
    * @param deletedShapes
    */
  def notifyObservers(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit = observers.foreach(_.update(newAgents, deletedShapes))

}