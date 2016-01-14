package fil.iagl.idl.bouncingballs

abstract class Agent(var position: Position) {

  var stepX = 0
  var stepY = 0

  def doIt(environment: Environment) : Unit

}
