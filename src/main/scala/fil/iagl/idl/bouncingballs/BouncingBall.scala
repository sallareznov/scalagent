package fil.iagl.idl.bouncingballs

class BouncingBall(position: Position, environment: Environment) extends Agent(position, environment) {

  override def doIt() : Unit = {
    // TODO
    println("doIt()")
  }

}

object BouncingBall {

  def apply(posX: Int, posY: Int, environment: Environment): Unit = {
    new BouncingBall(posX, posY, environment)
  }

}
