package fil.iagl.idl.scalagent.hunt

import org.backuity.clist._

object HuntCommand extends Command(name="hunt", description="a prey, guided by the user's directions, is chased by a defined number of predators, guided by Dijkstra's algorithm") {

  var nbHunters = opt[Int](name = "nbHunters", default = 1, description = "the number of hunters [default = 1]")
  var nbObstacles = opt[Int](name = "nbObstacles", default = 2, description = "the number of obstacles [default = 2]")
  var envWidth = opt[Int](name = "envWidth", default = 300, description = "the width of the grid representing the environment [default = 300]")
  var envHeight = opt[Int](name = "envHeight", default = 300, description = "the height of the grid representing the environment [default = 300]")
  var agentSize = opt[Double](name = "agentSize", default = 5, description = "the size of the agent (i.e the radius in pixels of the circles representing the particles) [default = 5]")
  var speed = opt[Int](name = "speed", default = 100, description = "the speed of the game (i.e. the number of milliseconds per lap) [default = 100]")

}
