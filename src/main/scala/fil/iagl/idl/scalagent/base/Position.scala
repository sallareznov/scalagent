package fil.iagl.idl.scalagent.base

/**
  * A position of an [[fil.iagl.idl.scalagent.base.Agent]] is represented by two coordinates : the abscissa and the ordinate.
  * @param x the abscissa
  * @param y the ordinate
  */
case class Position(x: Int, y: Int) {

  override def toString = "(" + x + ", " + y + ")"

}
