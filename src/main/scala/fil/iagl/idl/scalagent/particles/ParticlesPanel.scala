package fil.iagl.idl.scalagent.particles

import java.awt.{Color, Dimension, Graphics}
import javax.swing.JPanel

class ParticlesPanel(val gridSizeInPixels: Int, val particleSize: Int, var takenCells: Array[Array[Boolean]]) extends JPanel {

  def setTakenCells(takenCells: Array[Array[Boolean]]): Unit = this.takenCells = takenCells

  override def getPreferredSize(): Dimension = new Dimension(gridSizeInPixels, gridSizeInPixels)

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    println("paintComponent()")
    g.setColor(Color.RED)
    for (i <- takenCells.indices; j <- takenCells.indices) {
      if (takenCells(i)(j)) {
        println(s"(${i}, ${j})")
        g.drawRect(i * particleSize, j * particleSize, particleSize, particleSize)
        g.fillRect(i * particleSize, j * particleSize, particleSize, particleSize)
      }
    }
  }

}
