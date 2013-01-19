package tsp

object TravellingSalesman {
  
  case class City(x: Double, y: Double){
    def distTo(c: City): Double = {
      math.sqrt(math.pow(x-c.x,2) + math.pow(y-c.y,2) )
    }
  }

  def main(args: Array[String]): Unit = {
    val dub = new City(0,0)
    val kk = new City(3,4)
    
    println(kk.distTo(dub))
  }

}