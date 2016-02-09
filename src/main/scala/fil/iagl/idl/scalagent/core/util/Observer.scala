package fil.iagl.idl.scalagent.core.util

import javafx.scene.shape.Shape

import fil.iagl.idl.scalagent.core.model.Agent

import scala.collection.mutable

trait Observer {

  def update(newAgents: scala.collection.Set[Agent], deletedShapes: mutable.HashSet[Shape]): Unit

}