package fil.iagl.idl.scalagent.wator

import org.backuity.clist._

object WatorCommand extends Command(name = "wator", description = "blabla") {

  // -> command arguments
  var width = opt[Int](name = "width", default = 0, description = "the width of the grid representing the environment")
  var height = opt[Int](name = "height", default = 0, description = "the height of the grid representing the environment")
  var nTunas = opt[Int](name = "nTunas", default = 10000, description = "the number of tunas in the environment, distributed randomly")
  var nSharks = opt[Int](name = "nSharks", default = 5000, description = "the number of sharks in the environment, distributed randomly")
  var tBreed = opt[Int](name = "tBreed", default = 7, description = "the number of cycles a tuna must exist before reproducing")
  var sBreed = opt[Int](name = "sBreed", default = 5, description = "the number of cycles a shark must exist before reproducing")
  var starve = opt[Int](name = "starve", default = 5, description = "the number of cycles a shark has to find food before starving")

}
