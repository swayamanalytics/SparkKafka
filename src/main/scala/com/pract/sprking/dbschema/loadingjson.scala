package com.pract.sprking.dbschema
import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._
import java.sql.Timestamp
import com.typesafe.config.ConfigFactory
import java.time.LocalDateTime
import scala.concurrent._
import scala.concurrent.duration._
import scala.util.Try
object unparsed_dataschema {
  case class unparsed_data_row(input_json:String,createddate:Timestamp)
  class unparsed_data(tag:Tag) extends Table[unparsed_data_row](tag,"unparsed_data")
  {
    def input_json=column[String]("input_json")
    def createddate=column[Timestamp]("createddate")
    def * = (input_json,createddate) <> (unparsed_data_row.tupled,unparsed_data_row.unapply)
  }
  
  lazy val unparsed_dataQ=TableQuery[unparsed_data]  
}

object loadingjson  {
  
  val confg=ConfigFactory.load
  val dbcon=Database.forConfig("mysqlDB")
  val awaitedresult=dbcon.run(
      unparsed_dataschema.unparsed_dataQ.insertOrUpdate(unparsed_dataschema.unparsed_data_row("asdad",Timestamp.valueOf(LocalDateTime.now())))
      )
  import scala.util.{ Failure, Success }
  val result=Try(Await.result(awaitedresult,Duration.Inf)) recoverWith{ case t:Throwable => println("Some Error") ;Failure(t)}
}