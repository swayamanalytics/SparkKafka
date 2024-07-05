package test.com.pract.sprking.util
import org.scalatest.FunSuite
import java.util.NoSuchElementException
import org.scalatest.BeforeAndAfter
import org.scalatest.mockito.MockitoSugar
import com.pract.sprking.util._
import org.mockito.Mockito._

class testToParsingFile extends FunSuite with MockitoSugar with BeforeAndAfter{
  
 
  test("Check asam")
  {
    val ss=ParsingFile.readLi("Hello,world")
    assert(ss === Array("Hello","world"))
  }
  
  test("With mock")
  {
    val fdfd=mock[loginservice]
    when(fdfd.login("dsd", "xxxx")).thenReturn(Some(User("dsd")))
    val dsds=fdfd.login("dsd","xxxx")
    assert(dsds.get === User("dsd"))
        
  }
  test("With exception")
  {
  assume(ParsingFile.readLi("Hello,world")===Array("Hello","world"), "The database was down again")
  
}
}