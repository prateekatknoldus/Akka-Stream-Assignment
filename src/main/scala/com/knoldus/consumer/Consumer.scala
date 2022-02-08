package com.knoldus.consumer

import com.knoldus.model.File
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties
import scala.collection.JavaConverters._

object Consumer extends App {
  val prop: Properties = new Properties()

  prop.put("bootstrap.servers", "localhost:9092")
  prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  prop.put("value.deserializer", "com.knoldus.deserializer.FileDeserializer")
  prop.put("group.id", "test")

  val consumer = new KafkaConsumer[String, File](prop)

  val topics = List("fileRead")

  try {
    consumer.subscribe(topics.asJava)
    while (true) {
      val records = consumer.poll(10)
      if(!records.isEmpty)
        {
          for (record <- records.asScala) {
            val newFile = record.value()
            newFile.location
            println(record.value())
          }
        }

    }
  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    consumer.close()
  }
}
