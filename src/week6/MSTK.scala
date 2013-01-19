package week6

import scala.io.Source
import jgraph.MST


object MSTK extends App {
  // read in objects
  val in = Source.fromURL("http://spark-public.s3.amazonaws.com/algo2/datasets/edges.txt").getLines.toList
  
  // check size
  
  println ( "  size " + in.tail.size)
  
  println (in.head)
  
  var mst = new MST()
  
  for {
      line <- in.tail
      s = line.split("\\s+")
      src = Integer.parseInt(s(0))
      dest = Integer.parseInt(s(1))
      weight = Integer.parseInt(s(2))
      
    } mst.addEdge(src, dest, weight)
    
    println ("cost: " + mst.calculate() )
  
  
  
  

}