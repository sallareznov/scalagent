package fil.iagl.idl.scalagent.hunt.util

import org.backuity.clist._

object HuntCommandOptions extends Command(name="hunt", description="a prey, guided by the user's directions, is chased by a defined number of predators, guided by Dijkstra's algorithm") {

  var nbHunters = opt[Int](name = "nbHunters", default = 2, description = "the number of hunters [default = 2]")
  var nbObstacles = opt[Int](name = "nbObstacles", default = 50, description = "the number of obstacles [default = 50]")
  var envWidth = opt[Int](name = "envWidth", default = 100, description = "the width of the grid representing the environment [default = 100]")
  var envHeight = opt[Int](name = "envHeight", default = 100, description = "the height of the grid representing the environment [default = 100]")
  var agentSize = opt[Double](name = "agentSize", default = 2.5, description = "the size of the agent (i.e the radius in pixels of the circles representing the particles) [default = 5]")
  var speed = opt[Int](name = "speed", default = 100, description = "the speed of the game (i.e. the number of milliseconds per lap) [default = 100]")

}
