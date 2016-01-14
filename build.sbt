name := "bouncing-balls"
version := "1.0"
organization := "fil.iagl.idl.bouncingballs"
scalaVersion := "2.11.7"
mainClass in assembly := Some("fil.iagl.idl.bouncingballs.Main")
assemblyJarName in assembly := "bouncing-balls.jar"
libraryDependencies += "com.lihaoyi" %% "scalarx" % "0.2.8"
