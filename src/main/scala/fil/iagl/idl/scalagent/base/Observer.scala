package fil.iagl.idl.scalagent.base

trait Observer {

  def update(takenCells: Array[Array[Boolean]]): Unit

}
