name := "dm-exercises"

version := "0.0.1"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
//libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

lazy val root = (project in file(".")).
  aggregate(e1, e2, e3)

//lazy val commonSettings = Seq(
//  organization := "de.ironjan",
//  version := "0.1.0",
//  scalaVersion := "2.11.8"
//)


lazy val e1 =
  (project.in(file("e1")))
//    .settings(commonSettings: _*)
    .settings(/* other settings */)

lazy val e2 = project.in(file("e2"))
lazy val e3 = project.in(file("e3"))
