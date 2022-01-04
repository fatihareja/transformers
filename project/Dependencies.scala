import play.sbt.PlayImport.{ehcache, guice, ws}
import sbt._
object Dependencies {

  object Version {
    val commonExecutor = "1.3"
  }
  object Compile {
    val commonExecutor = "org.apache.commons" % "commons-exec" % Version.commonExecutor
  }

  import Compile._
  lazy val dependencies = Seq(
    ehcache,
    ws,
    guice,
    commonExecutor
  )


}
