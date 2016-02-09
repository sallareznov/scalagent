package fil.iagl.idl.scalagent.core

import scala.util.Random

/**
  * The Moore neighborhood (8 neighbors)
  */
class MooreNeighborhood extends Neighborhood {

  override def neighborPositions(position: Position): List[Position] =
    Random.shuffle(List(
      new Position(position.x + 1, position.y),
      new Position(position.x + 1, position.y + 1),
      new Position(position.x, position.y + 1),
      new Position(position.x - 1, position.y + 1),
      new Position(position.x - 1, position.y),
      new Position(position.x - 1, position.y - 1),
      new Position(position.x, position.y - 1),
      new Position(position.x + 1, position.y)
    ))

}
