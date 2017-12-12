name := "functional-encryption-chat"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.apache.commons" % "commons-math3" % "3.5"
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.17"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.8"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.8" % Test

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
