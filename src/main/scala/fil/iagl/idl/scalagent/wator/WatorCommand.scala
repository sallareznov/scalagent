package fil.iagl.idl.scalagent.wator

import org.backuity.clist._

object WatorCommand extends Command(name = "wator", description = "wator is a simulation of the interaction over time of tunas and sharks in a small rectangular area") {

  // -> command arguments
  var width = opt[Int](name = "width", default = 125, description = "the width of the grid representing the environment [default = 180]")
  var height = opt[Int](name = "height", default = 125, description = "the height of the grid representing the environment [default = 180]")
  var nTunas = opt[Int](name = "nTunas", default = 900, description = "the number of tunas in the environment, distributed randomly [default = 900]")
  var nSharks = opt[Int](name = "nSharks", default = 500, description = "the number of sharks in the environment, distributed randomly [default = 500]")
  var tBreed = opt[Int](name = "tBreed", default = 3, description = "the number of cycles a tuna must exist before reproducing [default = 3]")
  var sBreed = opt[Int](name = "sBreed", default = 9, description = "the number of cycles a shark must exist before reproducing [default = 9]")
  var starve = opt[Int](name = "starve", default = 5, description = "the number of cycles a shark has to find food before starving [default = 5]")
  var speed = opt[Int](name = "speed", default = 80, description = "the speed of the game (i.e. the number of milliseconds per lap) [default = 80]")

}
