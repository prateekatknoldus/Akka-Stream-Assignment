package com.knoldus.deserializer

import com.knoldus.model.File
import org.apache.kafka.common.serialization.Deserializer
import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

class FileDeserializer extends Deserializer[File] {
  override def configure(map: util.Map[String, _], b: Boolean): Unit = {
  }

  override def deserialize(topic: String, bytes: Array[Byte]): File = {
    val byteIn = new ByteArrayInputStream(bytes)
    val objIn = new ObjectInputStream(byteIn)
    val obj = objIn.readObject().asInstanceOf[File]
    byteIn.close()
    objIn.close()
    obj
  }

  override def close(): Unit = {
  }
}
