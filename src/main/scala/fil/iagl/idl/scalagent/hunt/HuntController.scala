package fil.iagl.idl.scalagent.hunt

import javafx.event.EventHandler
import javafx.scene.input.{KeyCode, KeyEvent}

import fil.iagl.idl.scalagent.core.Direction

class HuntController(model: HuntModel, view: HuntView) {

  def installKeyPressedEventHandler(): Unit = {
    view.scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler[KeyEvent] {
      override def handle(event: KeyEvent): Unit = {
        println("HANDLING")
        event.getCode match {
          case KeyCode.UP => model.prey.direction = Direction.NORTH
          case KeyCode.DOWN => model.prey.direction = Direction.SOUTH
          case KeyCode.RIGHT => model.prey.direction = Direction.EAST
          case KeyCode.LEFT => model.prey.direction = Direction.WEST
          case _ => ()
        }
      }
    })
  }

}
