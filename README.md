[![Codacy Badge](https://api.codacy.com/project/badge/grade/a86647308b7740b1850a6cf6a3598343)](https://www.codacy.com/app/sallareznov/scalagent)

# scalagent
Behaviour simulation, Multi-agent system approach using Scala/FX

Full report at : https://github.com/sallareznov/scalagent/tree/master/report.pdf

![usage](images/usage.png)

### Compilation

To compile the project, use the command sbt assembly. This will generate an
executable archive named `scalagent.jar` in the folder `target/scala-2.11/`

For example, if the user wants to launch the hunt application with default parameters, the
command to enter is the following:
```
$ java -jar target/scala-2.11/scalagent.jar hunt
```

If the user wants to use the particles applications with a toroidal grid of 15000 particles,
the command to enter is the following:
```
$ java -jar target/scala-2.11/scalagent.jar particles --nbParticles=15000 --toroidal=true
```

PS: For the wator application, the time-dependent number of tunas and sharks and the age
pyramid are drawn in real time, during the execution of the application.

### Screenshots

#### Particles

![particles](images/particles_screenshot.png)

#### Wator

![particles](images/wator_screenshot.png)

#### Hunt

![hunt](images/hunt_screenshot.png)


### UML

![overview](images/overview.png)

![particles-diagram](images/particles_diagram.png)

![wator-diagram](images/wator_diagram.png)

![hunt-diagram](images/hunt_diagram.png)
