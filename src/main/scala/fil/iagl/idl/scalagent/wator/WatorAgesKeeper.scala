package fil.iagl.idl.scalagent.wator

import scala.collection.mutable

/**
  * The tunas and sharks' ages collector
  */
object WatorAgesKeeper {

  /**
    * the ages of the tunas
    */
  val tunasAges = mutable.Map[Int, Int]()

  /**
    * the sharks of the tunas
    */
  val sharksAges = mutable.Map[Int, Int]()

  /**
    * increments the number of tunas aged `age`
    * @param age the age
    */
  def incrementNbTunasAged(age: Int): Unit = {
    tunasAges.get(age) match {
      case None => tunasAges += (age -> 1)
      case Some(x) => tunasAges.put(age, x + 1)
    }
  }

  /**
    * increments the number of sharks aged `age`
    * @param age the age
    */
  def incrementNbSharksAged(age: Int): Unit = {
    sharksAges.get(age) match {
      case None => sharksAges += (age -> 1)
      case Some(x) => sharksAges.put(age, x + 1)
    }
  }

}
