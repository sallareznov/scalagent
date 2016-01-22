package fil.iagl.idl.scalagent.wator

import fil.iagl.idl.scalagent.core.CommandWithHandling
import org.backuity.clist._

object WatorMainCommand extends Command(name = "wator", description = "blabla") with CommandWithHandling  {

  var width = opt[Int](name = "width", default = 0, description = "the width of the grid representing the environment")
  var height = opt[Int](name = "height", default = 0, description = "the height of the grid representing the environment")
  var nFishes = opt[Int](name = "nFishes", description = "the number of fishes in the environment, distributed randomly")
  var nSharks = opt[Int](name = "nSharks", description = "the number of sharks in the environment, distributed randomly")
  var fBreed = opt[Int](name = "fBreed", description = "the number of cycles a fish must exist before reproducing")
  var sBreed = opt[Int](name = "sBreed", description = "the number of cycles a shark must exist before reproducing")
  var starve = opt[Int](name = "fBreed", description = "the number of cycles a shark has to find food before starving")

  override def handleCommand(args: Array[String]) {
    Cli.parse(args).withCommand(WatorMainCommand.this) {
      case opt => {}
    }
  }

}
