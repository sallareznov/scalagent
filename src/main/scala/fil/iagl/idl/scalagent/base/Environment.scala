package fil.iagl.idl.scalagent.base

/**
  * The environment.
  */
class Environment(size: Int) {

  val takenCells = Array.ofDim[Boolean](size, size)

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