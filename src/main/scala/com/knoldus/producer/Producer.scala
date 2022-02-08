package com.knoldus.producer

import com.knoldus.model.File
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties

object Producer extends App {
  val props: Properties = new Properties()

  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "com.knoldus.serializer.FileSerializer")
  props.put("acks","all")

  val files = List(
    ("file1", "data/file1.json"),
    ("file2", "data/file2.json")
  )

  val producer = new KafkaProducer[String, File](props)
  try {
    for (i <- 0 to 1) {
      val file = File(files(i)._1, files(i)._2)
      val record = new ProducerRecord[String, File]("fileRead", i.toString, file)
      println(file)
      producer.send(record)
    }
  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    producer.close()
  }
}
