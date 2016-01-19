package fil.iagl.idl.scalagent.base

/**
  * The environment.
  */
class Environment(val size: Int) {

  val takenCells = Array.ofDim[Boolean](size, size)

  def mark(position: Position): Unit = (takenCells(position.x)(position.y) = true)

  def unmark(position: Position): Unit = (takenCells(position.x)(position.y) = false)

}

/**
  * Object companion
  */
object Environment {

  /**
    * initializes a new environment
    * @param size the size of the environment (number of rows and number of columns)
    */
  def apply(size: Int) = new Environment(size)


}