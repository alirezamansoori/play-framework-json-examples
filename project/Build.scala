import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

val appName = "y"
val appVersion = "1.0-SNAPSHOT"

val appDependencies = Seq(
// Add your project dependencies here,
javaCore
)

val main = play.Project(appName, appVersion, appDependencies)

// Add your own project settings here
}