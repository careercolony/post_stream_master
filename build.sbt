name := "post_stream_master"

version := "0.1"

scalaVersion := "2.11.8"



scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-optimise"
)
val sparkVersion = "2.0.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion

libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % sparkVersion

libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-8-assembly_2.11" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "1.6.0"

libraryDependencies += "com.typesafe" % "config" % "1.3.1"

libraryDependencies += "org.json4s" % "json4s-native_2.11" % "3.5.2"
libraryDependencies += "org.json4s" % "json4s-jackson_2.11" % "3.5.2"

libraryDependencies += "io.spray" % "spray-httpx_2.11" % "1.3.3"
libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"

libraryDependencies ++= {
  val sprayV = "1.3.3"
  val akkaV = "2.3.9"
  Seq(
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-client" % sprayV,
    "io.spray" %% "spray-caching" % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV
  )
}


libraryDependencies += "org.elasticsearch" %% "elasticsearch-spark-20" % "7.0.0-beta1"

libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "0.11.0.0" exclude("org.slf4j","slf4j-log4j12")

