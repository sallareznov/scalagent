package fil.iagl.idl.scalagent.core.util

/**
  * The type of an agent
  */
object AgentType {

  sealed trait Type
  case object PARTICLE extends Type
  case object TUNA extends Type
  case object SHARK extends Type
  case object PREDATOR extends Type
  case object PREY extends Type
  case object OBSTACLE extends Type
  case object NO_TYPE extends Type

}
