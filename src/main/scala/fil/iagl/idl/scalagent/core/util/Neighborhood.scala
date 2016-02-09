package fil.iagl.idl.scalagent.core.util

/**
  * The neighborhood of an agent
  */
trait Neighborhood {

  /**
    * returns the neighbor positions of `position`
    * @param position the position
    * @return the neighbor positions of `position`
    */
  def neighborPositions(position: Position): List[Position]

}
