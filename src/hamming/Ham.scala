package hamming

import scala.io.Source
import org.jgrapht.alg.util.UnionFind
import scala.collection.mutable.MutableList
import scala.collection.mutable.PriorityQueue



object Ham extends App {
	val in = Source.fromURL("http://spark-public.s3.amazonaws.com/algo2/datasets/clustering2.txt")
	val inIt = in.getLines
	//
	
	
	
	class Vertex(v: Int, _ham: Long, _bits: List[String]) extends Ordered[Vertex]{
	  val ham = _ham
	  val vertex = v
	  val bits = _bits
	  def compare(that: Vertex) = (this.vertex - that.vertex)
	  override def hashCode() = vertex
	  override def toString = "v" + vertex
	  //override def equals(that: Any) = that.isInstanceOf[Vertex] && that.asInstanceOf[Vertex].vertex == this.vertex
	}
	
	class uf(
	  set: java.util.Set[Vertex]
	 ) extends UnionFind[Vertex](set){
	    def size(): Int = {
	     val ss = super.getParentMap().entrySet()
	     var totes = 0
	     val iter = ss.iterator()
	     
	     while (iter.hasNext()){
	       val next = iter.next()
	       if (next.getKey == next.getValue) totes +=1
	     }
	     totes
	    }
	    
	    def print(): Unit = {
	      //println("rank: " + super.getRankMap())
	      //println("paree: " + size)
	      //println("pare: " + super.getParentMap().keySet().size())
	     // println("paree: " + super.getParentMap().keySet())
	    }
	}
	
	
	
	class Edge(_src: Vertex, _dest: Vertex, _cost: Long) extends Ordered[Edge]{
	  var cost = _cost
	  val src = _src
	  val dest = _dest
	  def compare(that: Edge) = (that.cost - this.cost).toInt
	}
	
	println (inIt.next)
	
	
	
	
	var differenceMap:Map[Long, Int] = Map()
	
	
	var powers24 = List[Long](1)
	differenceMap += (powers24.head -> 1)
	differenceMap += (0L -> 1)
	
	var i = 1
	
	while (i < 24){
	  powers24 = List[Long](powers24.head * 2) ++ powers24
	  i+=1
	}
	
	for {
	  i <- powers24
	  j <- powers24
	  if (i > j)
	    sum = i+j
	    dif = (i-j).abs
	}{
	  differenceMap += (i -> 1)
	  differenceMap += (sum -> 2)
	  differenceMap += (dif -> 2)
	}
	println("dm " + differenceMap)
	println("dm " + differenceMap.size)
	println(powers24.size + " " + powers24)
	
	
	
	def getHam(bits: List[String]): Long = {
	  val bitIt = bits.iterator
	  val powersIt = powers24.iterator
	  var total: Long = 1
	  
	  
	  
	  while (bitIt.hasNext){
	    
	    val x = bitIt.next
	    
	    val y = powersIt.next
	    if (x=="1") {
	      total += y
	    }
	  }
	  //println(total)
	  total
	}
	
	def realHam(v1: Vertex, v2: Vertex): Int = {
	  (v1.bits zip v2.bits).filterNot(p => p._1 == p._2).size
	  
	}
	
	val s1 = "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1"
	val s2 = "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1"
	
	println(s1)
	println(s2)
	
	val v2 = getHam(s1.split("\\s+").toList)
	val v3 = getHam(s2 .split("\\s+").toList)
	
	println(v2)
	println(v3)
	
	println ((v2 -v3).abs)
	println(differenceMap get (v2 -v3).abs)
	
	
	
	/*
	 * For each entry getHam then compare to previous hams and add to hams
	 * if (a>b) a/b else 
	 */
	
	// need mutable list here of vertices
	val vertices = new MutableList[Vertex]()
	// need mutable list of edges
	val minEdges = new PriorityQueue[Edge]()
	
	val vertexSet = new java.util.HashSet[Vertex]
	var index = 0;
	
	
	while (inIt.hasNext){
	  
	  
	  val bits = inIt.next.split("\\s+").toList
	  
	  val currHam = getHam(bits)
	  val currVertex = new Vertex(index, currHam, bits)
	  
	  vertices foreach (x => {
	    val y = (x.ham - currHam).abs
	    val diff = differenceMap get y
	    
	    diff match {
	      case Some(a) => {
	        val realDiff = realHam(currVertex, x)
	        if (realDiff <= 2) minEdges += new Edge(currVertex, x, realDiff)
	        
	      }
	      
	      case None =>
	    }
	    
	    
	  })
	  
	  vertices += currVertex
	  vertexSet.add(currVertex)
	  
	  
	  //println(index + ": " + minEdges.size)
	        
	  index+= 1
	  
	  
	}
	
	val unionFind = new uf(vertexSet)
	
	minEdges.foreach(edge => {
	  unionFind.union(edge.src, edge.dest)
	  println("dequeue " + edge)
	})
	
	println(" me " + minEdges.size)
	
	println("ufs " + unionFind.size)
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}