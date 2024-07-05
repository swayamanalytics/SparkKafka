package com.pract.sprking.dbschema

import com.typesafe.config.ConfigFactory
import java.io.FileReader
import java.io.BufferedReader
import java.io.File
import org.slf4j.Logger
import com.mysql.jdbc.log.LogFactory
import org.slf4j.LoggerFactory
import slick.jdbc.MySQLProfile.api._
import java.time.LocalDateTime
import slick.jdbc.MySQLProfile
import java.sql.Timestamp
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import java.time.LocalDate
import java.sql.Date

object jobsschema
{
  case class job(job_id:String,job_title:String,min_salary:Double,max_salary:Double)
  class jobs(tag:Tag) extends Table[job](tag,"jobs")
  {
   def job_id = column[String]("job_id")
   def job_title = column[String]("job_title")
   def min_salary = column[Double]("min_salary")
   def max_salary = column[Double]("max_salary")
   def * = (job_id,job_title,min_salary,max_salary) <> (job.tupled,job.unapply)
  }
  
  val jobsQ=TableQuery[jobs]
}
object employeesschema
{
  case class employee(employee_id:Int,first_name:String,last_name:String,email:String,phone_number:String,
      hire_date:Date,job_id:String,salary:Double,commission_pct:Double,manager_id:Int,department_id:Int)
  class employees(tag:Tag) extends Table[employee](tag,"employees")
  {
   def employee_id = column[Int]("employee_id")
   def first_name = column[String]("first_name")
   def last_name = column[String]("last_name")
   def email = column[String]("email")
   def phone_number = column[String]("phone_number")
   def hire_date = column[Date]("hire_date")
   def job_id = column[String]("job_id")
   def salary = column[Double]("salary")
   def commission_pct = column[Double]("commission_pct")
   def manager_id = column[Int]("manager_id")
   def department_id = column[Int]("department_id")
   def * = (employee_id,first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id) <> (employee.tupled,employee.unapply)
  }
  
  val employeesQ=TableQuery[employees]
}

object testmy extends App{
  
  val logger=LoggerFactory.getLogger(this.getClass)
  val conf=ConfigFactory.load()
  val dbconfig=Database.forConfig("mysqlDB")
  val awaitingresult=dbconfig.run(
      jobsschema.jobsQ.join(employeesschema.employeesQ).on(_.job_id === _.job_id).map{case (a,b) => (b.employee_id,a.job_title)}.result
//          join employeesschema.employeesQ on (_.job_id === _.job_id)).map{case (a,b) => (a.employee_id,b.job_title)}.result
 ) 
 import scala.util.{Failure, Success}
 awaitingresult.onComplete{
   case Success(s) => println(s.toList)
   case Failure(e) => println(e)
  }
 Thread.sleep(10000)
// val dfds=Await.result(awaitingresult, Duration.Inf)
// dfds.foreach(println)
 
 
  
//  val file=new File(conf.getString("filelevel.name"))
//  logger.debug("Going to read file"+file)
//  for(i <- resource.managed(new BufferedReader(new FileReader(file))))
//  {
//    var ss=Option(i.readLine())
//    while(ss != None)
//    {
//      println(ss.get)
//      ss=Option(i.readLine())
//    }
//  }
}