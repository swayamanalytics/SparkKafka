package com.pract.sprking.util

import com.typesafe.config.ConfigFactory

import slick.jdbc.MySQLProfile.api._
import scala.concurrent._
import scala.concurrent.duration._
import org.apache.commons.configuration.ConfigurationFactory
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import slick.jdbc.MySQLProfile
import scala.util.Try
import java.io.BufferedReader
import com.pract.sprking.dbschema._
import java.time.LocalDateTime
import java.sql.Timestamp
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

case class Studentcls(Id: Int, Name: String, Certificates: String)

class Studentp(tag: Tag) extends Table[Studentcls](tag, "Students") {
  def Id = column[Int]("Id")
  def Name = column[String]("Name")
  def Certificates = column[String]("Certificates")
  def * = (Id, Name, Certificates) <> (Studentcls.tupled, Studentcls.unapply)
}

object Entry extends App {

  val config = ConfigFactory.load()
  val db = Database.forConfig("mysqlDB")
  val logg=LoggerFactory.getLogger(this.getClass)
  val fi = new java.io.File(config.getString("filelevel.name"))
  var ss: Option[String] = None
  //2016-03-22 20:25:31,270001,99,EGO,540,31.00,B
  val linerex = "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}),(\\d+),(\\d+),([A-Z]+),(\\d+),(\\d+\\.\\d+),(\\w)".r

  try {
    for (input <- resource.managed(new BufferedReader(new java.io.FileReader(fi)))) {
      ss = Option(input.readLine)
      while (ss != None) {
        ss.get match {
          case linerex(trxdt, ordernum, ordercount, productcode, prevtrx, price, trxtype) => {
              logg.warn(ss.get)
//              println(ss.get)
//            val lis = ParsingFile.readLi(ss.get)
//            println(lis)
//            val fgf = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//            val insertst = db.run(
//              orderschema.OrderQ.insertOrUpdate(orderschema.orders(Timestamp.valueOf(LocalDateTime.parse(lis(0), fgf)), lis(1).toLong, lis(2).toInt, lis(3), lis(4).toInt, lis(5).toDouble, lis(6))))
//            Await.result(insertst, Duration.Inf)
//            ss = Option(input.readLine)
          }
          case _ => {
            logg.info("Error"+ss)
            
          }
        }
        ss = Option(input.readLine)
        

      }
    }

  } catch {
    case e: Throwable => println(e)
  }

  //  
  //  lazy val students = TableQuery[Studentp]
  //  val studentlist= db.run(
  //    students.insertOrUpdate(Studentcls(34,"asdas","dadsa")))
  //    
  //   
  //    val dd=Try(Await.result(studentlist, Duration.Inf)) 
  ////    recoverWith{
  ////    case t:Throwable => println("Some Error") ;Failure(t)
  ////  }
  //    dd match {
  //    case Success(line)=>println(line)
  //    case Failure(e)=> println(e)
  //  }

}