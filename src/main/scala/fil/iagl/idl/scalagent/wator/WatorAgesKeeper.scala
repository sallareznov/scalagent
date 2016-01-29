package fil.iagl.idl.scalagent.wator

import scala.collection.mutable

object WatorAgesKeeper {

  val tunasAges = mutable.Map[Int, Int]()
  val sharksAges = mutable.Map[Int, Int]()

  def saveTunaAge(age: Int): Unit = {
    tunasAges.get(age) match {
      case None => tunasAges += (age -> 1)
      case Some(x) => tunasAges.put(age, x + 1)
    }
  }

  def saveSharkAge(age: Int): Unit = {
    sharksAges.get(age) match {
      case None => sharksAges += (age -> 1)
      case Some(x) => sharksAges.put(age, x + 1)
    }
  }

}
