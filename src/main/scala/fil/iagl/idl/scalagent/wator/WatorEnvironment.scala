package fil.iagl.idl.scalagent.wator

import fil.iagl.idl.scalagent.core.{Position, Environment}
/**
  * Created by tello on 25/01/16.
  */
class WatorEnvironment(override val width: Int, override val height: Int) extends Environment(width,height) {
  var typeOfCell : Map[Position,Boolean] = Map()

  def setIsTuna (position: Position, isTuna: Boolean): Unit = {
    typeOfCell += (position -> isTuna)
  }


  def isTuna(position: Position): Boolean = {
    typeOfCell(position)
  }
}
