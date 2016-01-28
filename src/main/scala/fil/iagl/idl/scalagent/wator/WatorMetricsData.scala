package fil.iagl.idl.scalagent.wator

object WatorMetricsData {

  var nTunas = 0
  var nSharks = 0

  def incrementNTunas(): Unit = nTunas += 1

  def incrementNSharks(): Unit = nSharks += 1

  def decrementNTunas(): Unit = nTunas -= 1

  def decrementNSharks(): Unit = nSharks -= 1

}
