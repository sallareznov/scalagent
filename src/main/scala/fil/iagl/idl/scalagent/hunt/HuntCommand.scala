package fil.iagl.idl.scalagent.hunt

import org.backuity.clist._

object HuntCommand extends Command(name="hunt", description="blabla") {

  var nbHunters = opt[Int](name = "nbHunters", default = 2, description = "the number of hunters")
  var nbObstacles = opt[Int](name = "nbObstacles", default = 2, description = "the number of obstacles")
  var envWidth = opt[Int](name = "envWidth", default = 0, description = "the width of the grid representing the environment [default = the width of the screen]")
  var envHeight = opt[Int](name = "envHeight", default = 0, description = "the height of the grid representing the environment [default = the height of the screen]")
  var agentSize = opt[Double](name = "agentSize", default = 2.5, description = "the size of the agent (i.e the radius in pixels of the circles representing the particles) [default = 2.5]")


}
