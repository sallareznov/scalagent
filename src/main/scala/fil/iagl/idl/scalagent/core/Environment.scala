package fil.iagl.idl.scalagent.core

/**
  * The environment.
  */
class Environment(val width: Int, val height: Int) {

  val takenCells = Array.ofDim[Boolean](width, height)

  def mark(position: Position): Unit = (takenCells(position.x)(position.y) = true)

  def unmark(position: Position): Unit = (takenCells(position.x)(position.y) = false)

}

/**
  * Object companion
  */
object Environment {

  /**
    * initializes a new environment
    */
  def apply(width: Int, height: Int) = new Environment(width, height)


}