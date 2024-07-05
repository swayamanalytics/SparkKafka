package com.pract.sprking.dbschema

import java.time.LocalDateTime

import slick.jdbc.MySQLProfile.api._
import java.time.LocalDateTime
import slick.jdbc.MySQLProfile
import java.sql.Timestamp

object alldbscehma {
   
}

object orderschema {
  case class orders(dat:Timestamp,ordernum:Long,ordercount:Int,productcode:String,preve:Long,price:Double,typ:String)
  class Order(tag:Tag) extends Table[orders](tag,"orders")
  {
    def trxdt=column[Timestamp]("trxdate")
    def ordernum = column[Long]("ordernum")
    def ordercount =column[Int]("ordercount")
    def productcode=column[String]("productcode")
    def preve=column[Long]("prevtrx")
    def price=column[Double]("price")
    def typ=column[String]("trxtype")
    def * = (trxdt,ordernum,ordercount,productcode,preve,price,typ) <> (orders.tupled,orders.unapply)    
  }
  
  val OrderQ=TableQuery[Order]
}