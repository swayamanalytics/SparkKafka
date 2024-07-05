package com.pract.kafka
import org.apache.kafka.clients.consumer._
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConversions._
import java.util.Properties

object recivers extends App {
  
  val prop=new Properties();
  prop.put("bootstrap.servers", "192.168.131.3:9092")
  prop.put("group.id", "consumer-tutorial")
  prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  val kafkaconsumer=new KafkaConsumer[String,String](prop)
  kafkaconsumer.subscribe(List("sourav-mysql-topic"))
  
  while(true)
  {
   val records=kafkaconsumer.poll(1000) 
   for(i<-records.iterator())
   {
     println(i.value(),i.key(),i.partition(),i.offset(),i.topic())
   }
  }
}