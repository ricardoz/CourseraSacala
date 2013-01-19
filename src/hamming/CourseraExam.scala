package hamming

import com.gargoylesoftware.htmlunit._
import com.itextpdf.text.pdf._
import java.io.PrintWriter
import java.io.FileOutputStream
import com.itextpdf.text.pdf.parser.PdfReaderContentParser
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy

object CourseraExam extends App {
  
  import scala.io.Source
  //import HtmlUnit._
  
  val  webClient = new WebClient
  val  page = new HttpWebConnection(webClient)
  //val file = new java.net.URI("http://www.attheraces.com/images/atrform/racecards/20130101/20130101wolallcardsatrformtimeformracecardandformdata.pdf")
  
  //println(file)
  val reader = new PdfReader("http://www.attheraces.com/images/atrform/racecards/20130102/20130102linallcardsatrformtimeformracecard2page.pdf")
  val streamBytes = reader.getPageContent(2);
  
  import com.itextpdf.text.io.RandomAccessSourceFactory
  
  val parser = new PdfReaderContentParser(reader);
        //TextExtractionStrategy strategy;
        for (i <- 1 to reader.getNumberOfPages()) {
            val strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            println(strategy.getResultantText());
        }

}