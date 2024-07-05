package com.pract.sprking.util
import play.api.libs.json._
import play.api.libs.functional.syntax._
import org.apache.commons.lang.Validate

object scJson extends App{
  case class person(id:Int,first_name:String,last_name:String,email:String,gender:String,ip_address:String)
  val st=scala.io.Source.fromFile("C:\\Users\\Sourav\\Documents\\ScalaSpark\\com.pract.sprking\\src\\main\\resources\\dummy2.json").mkString
  
  implicit val jsch=new Reads[person]{
    def reads(j:JsValue)=JsSuccess(person((j\"id").as[Int],(j\"first_name").as[String],(j\"last_name").as[String],(j\"email").as[String],
        (j\"gender").as[String],(j\"ip_address").as[String]))       
  }
  
  val jss:JsValue=Json.parse(st)
  print(jss.validate[person])
//  val jperson=Json.parse(st).as[person]
//  print(jperson)
  
//  val jss:JsValue=Json.parse(st)
//  val jst=jss\\"guid"
//  println(jst)
//  val k = Json.toJson(Map("Name" -> "Rama","Age" -> "43"))
//  println(k)
//  case class person(name:String,age:Int)
//  implicit val jsonperson=Json.reads[person]
//  val jst="""{"name":"Rama","age":"23"}"""
//  val pp=Json.parse(jst).as[person]
  
}
