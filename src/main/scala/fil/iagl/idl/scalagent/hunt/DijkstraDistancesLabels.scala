package fil.iagl.idl.scalagent.hunt

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

/**
  * The labels to draw on the view
  */
object DijkstraDistancesLabels {

  /**
    * the labels
    */
  var labels: Option[Array[Array[Rectangle]]] = None

  /**
    * initializes the labels with the dimension of the environment
    * @param envWidth the width of the environment
    * @param envHeight the height of the environment
    */
  def initLabels(envWidth: Int, envHeight: Int): Unit = {
    labels = Some(Array.fill(envWidth, envHeight)(new Rectangle(4, 4, Color.GREEN)))
    for (i <- 0 until envHeight; j <- 0 until envHeight) {
      labels.get(i)(j).relocate(i, j)
    }
  }

  /**
    * changes the label at the position `(x, y)`
    * @param x the abscissa of the label
    * @param y the ordinate of the label
    * @param newValue the new value of the label
    */
  def changeLabel(x: Int, y: Int, newValue: Int): Unit = {
    labels.get(x)(y).setFill(new Color(0, (255 - newValue).toFloat / 255.toFloat, 0, 1))
  }

}
