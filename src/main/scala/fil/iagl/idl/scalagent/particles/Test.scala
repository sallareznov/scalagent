package fil.iagl.idl.scalagent.particles

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, ListView, TextField}
import scalafx.scene.layout.{GridPane, Priority, RowConstraints}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

/**
  * Created by salla on 19/01/16.
  */
object Test extends JFXApp {

  var mainContainer = new GridPane()

  stage = new JFXApp.PrimaryStage {
    title.value = "Particles"
    mainContainer.prefWidth = 1000
    mainContainer.prefHeight = 1000
    scene = new Scene {
      root = mainContainer
      val cons1 = new RowConstraints
      cons1.setVgrow(Priority.NEVER)

      val cons2 = new RowConstraints
      cons2.setVgrow(Priority.ALWAYS)

      mainContainer.getRowConstraints.addAll(cons1, cons2)

      val label = Label("Name:")
      val field = new TextField()
      val view = new ListView()
      val okBtn = new Button("OK")
      val closeBtn = new Button("Close")
      for (i <- 0 until 100; j <- 0 until 100) {
        val rect = new Rectangle {
          width = 10
          height = 10
          fill = if (i == 50) Color.Green else Color.Blue
        }
        mainContainer.add(rect, i, j)
      }
    }
  }

}
