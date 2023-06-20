name := """transaction-service"""
organization := "com.github"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.4"
libraryDependencies += "org.flywaydb" % "flyway-core" % "9.16.0"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.11.0" exclude("com.fasterxml.jackson.core", "jackson-databind")
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.3.0"




// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.github.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.github.binders._"
