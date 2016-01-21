package fil.iagl.idl.scalagent.base

import javafx.scene.paint.Color

import scala.util.Random


object Colors {

  val randomizer = new Random()

  def randomColor(): Color = {
    val randomRed = randomizer.nextDouble()
    val randomGreen = randomizer.nextDouble()
    val randomBlue = randomizer.nextDouble()
    val opacity = 1
    new Color(randomRed, randomGreen, randomBlue, opacity)
  }

}
