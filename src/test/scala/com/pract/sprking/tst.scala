package com.pract.sprking
import org.scalatest.FunSuite
import java.util.NoSuchElementException
import org.scalatest.BeforeAndAfter
import org.scalamock.scalatest.MockFactory
class tst extends FunSuite with BeforeAndAfter{
  var li=List()
  before
  {
    li:+45
  }
  test("An Empty List")
  {
    assert(li.isEmpty)
  }
  
  test("handing excpetion")
  {
    intercept[NoSuchElementException] {
    List.empty.head
  }
  }
  after
  {
    li=List()
  }
  
}