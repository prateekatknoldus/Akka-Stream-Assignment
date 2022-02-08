package com.knoldus.serializer

import com.knoldus.model.File
import org.apache.kafka.common.serialization.Serializer
import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util

class FileSerializer extends Serializer[File]{
  override def configure(map: util.Map[String, _], b: Boolean): Unit = {
  }

  override def serialize(topic: String, file: File): Array[Byte] = {
    try {
    val byteOut = new ByteArrayOutputStream()
    val objOut = new ObjectOutputStream(byteOut)
    objOut.writeObject(file).toString
    objOut.close()
    byteOut.close()
    byteOut.toByteArray
  }    catch {
    case ex:Exception => throw new Exception(ex.getMessage)
  }
  }

  override def close(): Unit = {
  }
}