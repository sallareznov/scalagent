package fil.iagl.idl.bouncingballs

/**
  * A bouncing ball is an agent which is a ball that can bounce.
  * @param position the coordinates of the agent in its surrounding environment
  */
class BouncingBall(position: Position) extends Agent(position) {

  override def doIt(environment: Environment) : Unit = {
    // TODO
    println("doIt()")
  }

}

/**
  * Companion object
  */
object BouncingBall {

  def apply(position: Position): Unit = {
    new BouncingBall(position)
  }

}
