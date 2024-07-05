package com.pract.sprking.util

import org.apache.spark.sql.SparkSession

object SaprkingChk extends App{
  val spark=SparkSession.builder.appName("TT").master("local[2]").getOrCreate()
  val RDD=spark.sparkContext.parallelize(1.to(20).toList)
  val rddsds=RDD.map(x=>x.toString()+"XX")
  rddsds.collect()
  
}