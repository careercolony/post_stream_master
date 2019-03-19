package streams

import akka.actor._
import config.Application
import kafka.serializer.StringDecoder
import model.Post
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.elasticsearch.spark._
import org.json4s.DefaultFormats
import spray.json._

object PostStream extends App with Application {

  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("kafka").setLevel(Level.ERROR)
  implicit val formats = DefaultFormats

  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("Post_Stream")
    .set("es.index.auto.create", "true")
    .set("es.nodes", elasticUrl)
    .set("es.nodes.wan.only", "true")

    .set("es.port", elasticPort)
  val ssc = new StreamingContext(conf, Seconds(windowDuration.toLong))

  import org.apache.spark.sql.SparkSession

  val spark = SparkSession
    .builder()
    .config(conf)
    .getOrCreate()

  val sparkCtx = ssc.sparkContext

  val sqlContext = spark.sqlContext

  println("*** PostStream ***")

  val _system = ActorSystem("POST")


  val post_msgs = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, brokersMap, postTopic)


  val postStream = post_msgs
    .map(x => {
      implicit val formats = DefaultFormats
      import model.JsonRepo.PostUpdateRequestFormats
      println("data from post topic:" + x._2)
      x._2.parseJson.convertTo[Post].toJson
    })

  postStream.foreachRDD((trdd: RDD[JsValue]) => {
    trdd.saveToEs(elasticindex + "/test_index")
  })
  ssc.start()

  ssc.awaitTermination()

}