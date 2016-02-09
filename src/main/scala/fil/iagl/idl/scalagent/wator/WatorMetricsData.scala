package fil.iagl.idl.scalagent.wator

/**
  * Wator metrics for the time-dependent number of tunas and sharks
  */
object WatorMetricsData {

  /**
    * the number of tunas
    */
  var nTunas = 0

  /**
    * the number of sharks
    */
  var nSharks = 0

  /**
    * increments the number of tunas
    */
  def incrementNTunas(): Unit = nTunas += 1

  /**
    * increments the number of sharks
    */
  def incrementNSharks(): Unit = nSharks += 1

  /**
    * decrements the number of tunas
    */
  def decrementNTunas(): Unit = nTunas -= 1

  /**
    * decrements the number of sharks
    */
  def decrementNSharks(): Unit = nSharks -= 1

}
