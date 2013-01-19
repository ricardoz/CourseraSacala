package shortestpairs

import edu.princeton.cs.algs4._

object FloydWarshall  {
  
  import scala.collection.JavaConversions._
  
  implicit def stringToInt(s: String): Int = Integer.parseInt(s)
  implicit def doubleToInt(d: Double): Int = d.toInt
  implicit def stringToDouble(s: String): Double = Integer.parseInt(s).toDouble
  
  
  case class FloydWarshall(G: AdjMatrixEdgeWeightedDigraph){
    val V = G.V
    val distTo = Array.ofDim[Int](V,V)
    val edgeTo = Array.ofDim[DirectedEdge](V,V)
    var negCyc = false
    var min = 0
    
    
    
    def hasNegativeCycle(): Boolean = (0 until V).exists(x => distTo(x)(x) < 0)
    
    def dist(s: Int, v: Int): Double = distTo(s)(v)
    
    for {
      i <- 0 until V
      j <- 0 until V
    }{
      distTo(i)(j) = 0
    }
    
    for {
      v <- 0 until V
      e <- G.adj(v).iterator()
    }{
      distTo(e.from)(e.to) = e.weight
      edgeTo(e.from)(e.to) = e
      
      if (distTo(v)(v) > 0){
        distTo(v)(v) = 0
        edgeTo(v)(v) = null;
      }
    }
    
    
    //Floyd Warshall updates
    for {
      i <- 0 until V
      v <- 0 until V
      
    }{
      if (edgeTo(v)(i) != null){
        for (w <- 0 until V){
          if (distTo(v)(w) > distTo(v)(i) + distTo(i)(w) ){
            distTo(v)(w) = distTo(v)(i) + distTo(i)(w)
            
            edgeTo(v)(w) = edgeTo(i)(w)
            
            if (distTo(v)(w) < min){
              min = distTo(v)(w)
              println(v + " " + w + ": " + min)
            } 
          }
          
          if (distTo(v)(v) < 0) negCyc = true;
        }
      } 
      
      
      
      
    }
    
    println("Minimum " + min)
  }

  def main(args: Array[String]): Unit = {
    
    import scala.io.Source
    
    val in = Source.fromURL("http://spark-public.s3.amazonaws.com/algo2/datasets/g2.txt").getLines
    
    val head = in.next.split("\\s+")
    
    println (head(0))
    
    val G = new AdjMatrixEdgeWeightedDigraph(head(0))
    
    //println(G)
    
    //G.addEdge(new DirectedEdge(7,10,5))
    
    println
    //println(G)
    
    while (in.hasNext){
      val next = in.next
      //println(next)
      val edge = next.split("\\s+")
      val e = G.addEdge(new DirectedEdge(Integer.parseInt(edge(0))-1, Integer.parseInt(edge(1))-1, edge(2)))
    }
    
    val FW = new FloydWarshall(G)
    println("Neg Cycle: " + FW.hasNegativeCycle)
    
    //println(G)
    println()
    
    //println(G)
    
    
    
  }

}