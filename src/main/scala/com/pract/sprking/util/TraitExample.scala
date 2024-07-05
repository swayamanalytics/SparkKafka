package com.pract.sprking.util
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
 import scala.concurrent.duration._
object TraitExample extends App{
 
  def pleasesayHello(subs:String)=Future{
    Thread.sleep(30000)
    println("Hello"+subs)
    
  }
  
  val df=Await.result(pleasesayHello("jasd"), 5.minutes)
  println("Hi")
  
}