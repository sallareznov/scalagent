package fil.iagl.idl.scalagent.core

import javafx.scene.shape.Shape

import scala.collection.mutable

trait Observer {

  def update(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit

}