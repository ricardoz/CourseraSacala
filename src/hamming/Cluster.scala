package hamming

import scala.io.Source
import org.jgrapht.alg.util.UnionFind
import scala.collection.mutable.MutableList
import scala.collection.mutable.PriorityQueue

object Cluster extends App {
	val in = Source.fromURL("http://spark-public.s3.amazonaws.com/algo2/datasets/clustering1.txt")
	val inIt = in.getLines
	
	class Vertex(v: Int) extends Ordered[Vertex]{
	  
	  val vertex = v
	  def compare(that: Vertex) = (this.vertex - that.vertex)
	  override def hashCode() = vertex
	  override def toString = "v" + vertex
	  override def equals(that: Any) = that.isInstanceOf[Vertex] && that.asInstanceOf[Vertex].vertex == this.vertex
	}
	
	class uf(
	  set: java.util.Set[Vertex]
	 ) extends UnionFind[Vertex](set){
	    def size(): Int = {
	     val ss = super.getParentMap().entrySet()
	     //println(ss)
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
	
	
	
	class Edge(_src: Vertex, _dest: Vertex, _cost: Int) extends Ordered[Edge]{
	  val cost = _cost
	  val src = _src
	  val dest = _dest
	  def compare(that: Edge) = (that.cost - this.cost)
	  override def toString() = "Right " + this.src + "->" + this.dest + "  =  " + cost
	}
	
	class rEdge(_edge: Edge) extends Ordered[rEdge]{
	  val edge = _edge
	  def compare(that: rEdge) = (that.edge.cost - this.edge.cost)
	}
	
	println (inIt.next)
	
	/*
	 * minEdges is minimum priority queue
	 * maxEdges reverses edge cost to create max priority queue
	 */
	val minEdges = new PriorityQueue[Edge]()
	val maxEdges = new PriorityQueue[rEdge]()
	val vertexSet = new java.util.HashSet[Vertex]
	
	/*
	 * Add to the vertex set
	 */
	var index = 1
	while (index < 501){
	  vertexSet.add(new Vertex(index))
	  index+=1
	}
	
	println(vertexSet)
	val unionFind = new uf(vertexSet)
	
	def readEntry(input: String): Unit = {
	  val entry = input.split("\\s+")
	  val src = new Vertex(Integer.parseInt(entry(0)))
	  val dest = new Vertex(Integer.parseInt(entry(1)))
	  val cost = Integer.parseInt(entry(2))
	  
	  minEdges += new Edge(src,dest,cost)
	}
	
	while (inIt.hasNext){
	  readEntry(inIt.next)
	}
	
	var notFound = true
	var cost = 11110
	
	while (unionFind.size  > 4){
	  //println("uf size " + unionFind.size)
	  val edge =  minEdges.dequeue
	  println("edge dequeued" + edge)
	  unionFind.union(edge.src, edge.dest)
	  maxEdges.+=(new rEdge(edge))
	}
	
	minEdges foreach (x => if (x.cost < cost) {
	  
	  val src = unionFind.find(x.src)
	  
	  val dest = unionFind.find(x.dest)
	  
	  
	  if (src != dest) {
	    println ("Right " + src + "->" + dest + "  =  " + x.cost)
	    
	    cost = x.cost
	   
	  } else {
	    //println ("Wrong " + src + "->" + dest + "  =  " + x.cost)
	  }
	  
	  
	} )
	
	println(" min edges left " + minEdges.size)
	
	/*var maxEdge 
	
	while (notFound){
	  val edge =  maxEdges.dequeue
	  val src = unionFind.find(edge.src)
	  println(src)
	  val dest = unionFind.find(edge.dest)
	  println(dest)
	  
	  if (src != dest) notFound = false
	  
	  println("size " + edge.cost)
	}*/
	
	
	
	
	
	//println("max dis " + minEdges.dequeue.cost)
	
	
	
	
	
	
	
	
	
	
	
	
}