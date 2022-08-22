name := """pokemon-api"""
organization := "sofka"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test

//Dependencias añadidas para usar Java 17 con SBT 2.8.16
libraryDependencies ++= Seq(
  "com.google.inject"            % "guice"                % "5.1.0",
  "com.google.inject.extensions" % "guice-assistedinject" % "5.1.0",
  "org.scalatestplus" %% "mockito-3-4" % "3.3.0.0-SNAP3" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "sofka.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "sofka.binders._"
