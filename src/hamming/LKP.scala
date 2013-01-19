package hamming

import scala.io.Source

object LKP extends App{
  
  val in = Source.fromURL("http://spark-public.s3.amazonaws.com/algo2/datasets/knapsack2.txt")
  val inIt = in.getLines
  
  val first = inIt.next.split("\\s+")
  val capacity = Integer.parseInt(first(0))
  val maxItems = Integer.parseInt(first(1))
  
  var cache: Map[Int, Long] = Map()
  
  println(capacity, maxItems)
  
  //val value = Array.ofDim[Int](capacity + 1, maxItems + 1)
  //val keep = Array.ofDim[Int](capacity + 1, maxItems + 1)
  
  //for (c <- 0 to capacity) value(c)(0) = 0
  //for (n <- 0 to maxItems) value(0)(n) = 0
  
  
  
  class Item(_value: Int, _size:Int) extends Ordered[Item]{
    val value = _value
    val size = _size
    val ratio = 10000000 * value / size
    override def toString() = "Value: " + value + " Size: " + size
    def compare(that: Item): Int = that.ratio - this.ratio
    
    
  }
  
  var items: List[Item] = List()
  //var index = 1
  
  var maxValue: Long = 100000;
  
  def getValue(cap: Int): Long = {
      cache get cap match {
        case Some(a) => a
	      
	    case None => 0
      } 
    }
  
  while (inIt.hasNext){
    val token = inIt.next.split("\\s+")
    
    //println(token)
    
    val _size = Integer.parseInt(token(1))
    
    val _value = Integer.parseInt(token(0))
    
    val item = new Item(_value, _size)
    
    println(item)
    
    
    //items = List[Item](item) ++ items
    
    for (c <- capacity to 1 by -1) {
      
      //println(c)
      
      //val curr = value(c)(index)
      
      val prev = getValue(c)
      
      // with one less item
      
      // if item fits (
      if (c >= item.size ) {
        
        var currValue: Long = item.value
        // if space left
        if (c-item.size > 0) {
          
          currValue += getValue(c-item.size)
        } 
        
        
        if (currValue > prev) {
          cache += (c -> currValue)
          
          
          if (currValue > maxValue){
            maxValue = currValue
          println("New MV: " + maxValue)
          println("Item: " + item.value + " Prev " + getValue(c-item.size))
          println()
          }
          
        } 
        
      } 
    }
      
    
  }

}