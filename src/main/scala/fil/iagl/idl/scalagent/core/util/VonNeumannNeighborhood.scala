package fil.iagl.idl.scalagent.core.util

import scala.util.Random

/**
  * The Von Neumann neighborhood (4 neighbors)
  */
class VonNeumannNeighborhood extends Neighborhood {

  override def neighborPositions(position: Position): List[Position] =
    Random.shuffle(List(
      new Position(position.x + 1, position.y),
      new Position(position.x, position.y + 1),
      new Position(position.x - 1, position.y),
      new Position(position.x, position.y - 1)
    ))

}
