package fil.iagl.idl.scalagent.particles

import java.awt.{Toolkit, Dimension, GridLayout}
import javax.swing.JFrame

import fil.iagl.idl.scalagent.base.Observer

class ViewSwing(val envSize: Int, val agentSize: Int) extends Observer {

  val frame = new JFrame()
  var visibility = false
  val gridSizeInPixels = envSize * agentSize
  val screenDimensions = Toolkit.getDefaultToolkit.getScreenSize
  val container = new ParticlesPanel(screenDimensions.getHeight.asInstanceOf[Int], agentSize, Array.ofDim(agentSize, agentSize))
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  container.setPreferredSize(new Dimension(envSize * agentSize, envSize * agentSize))
  container.setLayout(new GridLayout(gridSizeInPixels, gridSizeInPixels))
  frame.setContentPane(container)
  frame.pack
  frame.setVisible(true)

  override def update(takenCells: Array[Array[Boolean]]): Unit = {
    println("update()")
    container.setTakenCells(takenCells)
    container.repaint()
  }

}

