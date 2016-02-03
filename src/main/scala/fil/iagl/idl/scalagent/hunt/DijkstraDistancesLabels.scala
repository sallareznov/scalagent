package fil.iagl.idl.scalagent.hunt

import javafx.scene.control.Label

object DijkstraDistancesLabels {

  var labels: Option[Array[Array[Label]]] = None

  def initLabels(envWidth: Int, envHeight: Int): Unit = labels = Some(Array.fill(envWidth, envHeight)(new Label("-1")))

  def changeLabel(x: Int, y: Int, newValue: Int): Unit = labels.get(x)(y) = new Label(newValue.toString)

}
