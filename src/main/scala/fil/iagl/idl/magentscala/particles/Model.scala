package fil.iagl.idl.magentscala.particles

import java.util.Observable

import fil.iagl.idl.magentscala.base.{Environment, Agent}

import scala.util.Random

/**
  * The model, referencing the environment and the agents inside. The model is the
  * [[java.util.Observable]] of a [[fil.iagl.idl.magentscala.particles.View]]; therefore,
  * the latter can update itself after a lap (a lap is completed when all agents have computed
  * their next move).
  * @param environment the environment
  * @param agents the agents
  */
class Model(val environment: Environment, var agents: Array[Agent]) extends Observable {

  /**
    * initializes the model
    * @param nbOfAgents the number of agents in the environment
    */
  def init(nbOfAgents: Int): Unit = {
    agents = new Array[Agent](nbOfAgents)
    // TODO initialize the environment
    //environment = Environment()
  }

  /**
    * runs the model (simulates the agents' moves)
    * @param nbOfLaps the number of laps to be completed
    */
  def run(nbOfLaps: Int): Unit = {
    agents = Random.shuffle(agents).toArray
    agents.foreach(_.doIt(environment))
    setChanged()
    notifyObservers()
    Thread.sleep(500)
  }

}
