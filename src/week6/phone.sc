import scala.io.Source

object phone {
	val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords")
                                                  //> java.io.FileNotFoundException: http://lamp.epfl.ch/files/content/sites/lamp/
                                                  //| files/teaching/progfun/linuxwords
                                                  //| 	at sun.net.www.protocol.http.HttpURLConnection.getInputStream(Unknown So
                                                  //| urce)
                                                  //| 	at java.net.URL.openStream(Unknown Source)
                                                  //| 	at scala.io.Source$.fromURL(Source.scala:143)
                                                  //| 	at scala.io.Source$.fromURL(Source.scala:133)
                                                  //| 	at phone$$anonfun$main$1.apply$mcV$sp(phone.scala:4)
                                                  //| 	at scala.runtime.WorksheetSupport$$anonfun$$execute$1.apply$mcV$sp(Works
                                                  //| heetSupport.scala:75)
                                                  //| 	at scala.runtime.WorksheetSupport$.redirected(WorksheetSupport.scala:64)
                                                  //| 
                                                  //| 	at scala.runtime.WorksheetSupport$.$execute(WorksheetSupport.scala:74)
                                                  //| 	at phone$.main(phone.scala:3)
                                                  //| 	at phone.main(phone.scala)
	
	
}