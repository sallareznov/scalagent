name := "scalagent"
version := "1.0"
organization := "fil.iagl.idl.scalagent"
scalaVersion := "2.11.7"
mainClass in assembly := Some("fil.iagl.idl.scalagent.particles.ParticlesView")
assemblyJarName in assembly := "scalagent.jar"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "8.0.60-R9",
  "org.backuity.clist" %% "clist-core"   % "2.0.1",
  "org.backuity.clist" %% "clist-macros" % "2.0.1" % "provided"
)