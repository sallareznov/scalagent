package fil.iagl.idl.scalagent.wator

import org.backuity.clist._

object WatorCommand extends Command(name = "wator", description = "blabla") {

  // -> command arguments
  var width = opt[Int](name = "width", default = 180, description = "the width of the grid representing the environment")
  var height = opt[Int](name = "height", default = 180, description = "the height of the grid representing the environment")
  var nTunas = opt[Int](name = "nTunas", default = 900, description = "the number of tunas in the environment, distributed randomly")
  var nSharks = opt[Int](name = "nSharks", default = 100, description = "the number of sharks in the environment, distributed randomly")
  var tBreed = opt[Int](name = "tBreed", default = 2, description = "the number of cycles a tuna must exist before reproducing")
  var sBreed = opt[Int](name = "sBreed", default = 8, description = "the number of cycles a shark must exist before reproducing")
  var starve = opt[Int](name = "starve", default = 3, description = "the number of cycles a shark has to find food before starving")
  var speed = opt[Int](name = "speed", default = 100, description = "the speed of the game (i.e. the number of milliseconds per lap)")

}
