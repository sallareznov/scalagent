package fil.iagl.idl.bouncingballs

class Environment(size: Int) {

  def this(size: Int) {
    val takenPlaces: Array[Boolean][Boolean]
  }


}

object Environment {

  def apply(size: Int): Unit = {
    new Environment()
  }

}