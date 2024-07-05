package com.pract.sprking.util

import java.time.LocalDateTime
import java.sql.Timestamp

case class User(name:String)

trait loginservice
{
  def login(name:String,password:String):Option[User]
}

class ParsingFile(dat:LocalDateTime,ordernum:Long,ordercount:Int,st:String,preve:Long,prince:Double,typ:String) {
  
}

object ParsingFile {
  
  def readLi(s:String)={
    val lis:Array[String]=s.split(",")
   
    lis
//    new ParsingFile(LocalDateTime.parse(lis(0),fgf),lis(1).toLong,lis(2).toInt,lis(3),lis(4).toInt,lis(5).toLong,lis(6))
  }
}