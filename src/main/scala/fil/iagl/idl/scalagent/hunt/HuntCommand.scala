package fil.iagl.idl.scalagent.hunt

import org.backuity.clist._

object HuntCommand extends Command(name="hunt", description="blabla") {

  var nbHunters = opt[Int](name = "nbHunters", default = 2, description = "the number of hunters")

}
