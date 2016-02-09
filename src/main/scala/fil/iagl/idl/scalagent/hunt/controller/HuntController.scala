package fil.iagl.idl.scalagent.hunt.controller

import javafx.event.EventHandler
import javafx.scene.input.{KeyCode, KeyEvent}
import fil.iagl.idl.scalagent.core.util.Direction
import fil.iagl.idl.scalagent.hunt.model.HuntModel
import fil.iagl.idl.scalagent.hunt.view.HuntView

class HuntController(model: HuntModel, view: HuntView) {

  def installKeyPressedEventHandler(): Unit = {
    view.scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler[KeyEvent] {
      override def handle(event: KeyEvent): Unit = {
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
