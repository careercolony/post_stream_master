package config

import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConversions._


trait Application {

  val config: Config = ConfigFactory.load("application.conf")
  val postTopic   = config.getStringList("kafka.postTopic").map(_.toString).toSet
  val brokers: String = config.getString("kafka.brokers")
  val groupId: String = config.getString("kafka.groupId")
  val brokersMap = Map[String, String]("bootstrap.servers" -> brokers,"group.id"->groupId,"auto.offset.reset"->"largest")
  val windowDuration: String = config.getString("window-duration")

  val elasticUrl: String = config.getString("elastic.url")
  val elasticPort: String = config.getString("elastic.port")
  val elasticindex: String = config.getString("elastic.index")


}
