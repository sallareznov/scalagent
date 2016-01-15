package fil.iagl.idl.scalagent.base

/**
  * The environment.
  * @param takenCells the states of each cell (taken or free) in the environment
  */
class Environment(val takenCells: Array[Array[Boolean]]) {



}

/**
  * Object companion
  */
object Environment {

  /**
    * initializes a new environment
    * @param size the size of the environment (number of rows and number of columns)
    */
  def apply(size: Int): Unit = {
  //  new Environment()
  }

}