name := "scala-weekend-1-exercises"

version := "1.0"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scala-lang.modules" %% "scala-async" % "0.9.7",
  "com.github.nscala-time" %% "nscala-time" % "2.22.0",
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
  "com.storm-enroute" %% "scalameter" % "0.10.1" % "test" excludeAll ExclusionRule(organization = "org.mongodb"),
  "com.typesafe.akka" %% "akka-actor" % "2.5.21",
  "com.typesafe.akka" %% "akka-stream" % "2.5.21",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.21",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.21" % "test",
  "com.typesafe.akka" %% "akka-http" % "10.1.7",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.7" % "test",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7",
  "io.spray" %% "spray-json" % "1.3.5",
  "com.h2database" % "h2" % "1.4.197",
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.tpolecat" %% "doobie-core" % "0.5.1",
  "org.scalikejdbc" %% "scalikejdbc" % "3.3.2",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.2",
  "org.scalikejdbc" %% "scalikejdbc-test" % "3.3.2" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.playframework.anorm" %% "anorm" % "2.6.2",
  "org.mongodb" %% "casbah" % "3.1.1",
  "com.github.salat" %% "salat" % "1.11.2",
  "com.github.fakemongo" % "fongo" % "2.1.1",
  "com.github.tototoshi" %% "scala-csv" % "1.3.5"
)