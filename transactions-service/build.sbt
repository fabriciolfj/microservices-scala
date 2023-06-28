name := """transaction-service"""
organization := "com.github"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.4"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.3.0"
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.8.2"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.8.2"
libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % "2.8.2"
libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.8.15"
libraryDependencies += "com.typesafe.akka" %% "akka-protobuf-v3" % "2.8.2"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.2"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.github.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.github.binders._"
