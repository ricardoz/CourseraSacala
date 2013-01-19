package hamming

import scala.io.Source

object KnapsackProblem extends App {
  
  val in = Source.fromURL("http://spark-public.s3.amazonaws.com/algo2/datasets/knapsack1.txt")
  val inIt = in.getLines
  
  val first = inIt.next.split("\\s+")
  val capacity = Integer.parseInt(first(0))
  val maxItems = Integer.parseInt(first(1))
  
  println(capacity, maxItems)
  
  val value = Array.ofDim[Int](capacity + 1, maxItems + 1)
  val keep = Array.ofDim[Int](capacity + 1, maxItems + 1)
  
  for (c <- 0 to capacity) value(c)(0) = 0
  for (n <- 0 to maxItems) value(0)(n) = 0
  
  
  
  class Item(_value: Int, _size:Int){
    val value = _value
    val size = _size
  }
  
  var items: List[Item] = List()
  var index = 1
  
  var maxValue = 400000;
  
  while (inIt.hasNext){
    val token = inIt.next.split("\\s+")
    
    //println(token)
    
    val _size = Integer.parseInt(token(1))
    
    val _value = Integer.parseInt(token(0))
    
    val item = new Item(_value, _size)
    
    
    items = List[Item](item) ++ items
    
    for (c <- 1 to capacity) {
      //val curr = value(c)(index)
      val prev = value(c)(index -1) // with one less item
      
      // if item fits (
      if (c >= item.size ) {
        
        var currValue = item.value
        // if space left
        if (c-item.size > 0) {
          
          currValue += value(c-item.size)(index-1)
        } 
        
        
        if (currValue > prev) {
          value(c)(index) = currValue
          keep(c)(index) = 1
          
          if (currValue > maxValue){
            maxValue = currValue
          println("New MV: " + maxValue)
          println("Item: " + item.value + " Prev " + value(c-item.size)(index-1))
          println()
          }
          
        } else {
          value(c)(index) = prev
          keep(c)(index) = 0
        }
        
      } else {
        value(c)(index) = value(c)(index -1)
        keep(c)(index) = 0
      }
    }
      
    index+=1
  }
  
  //println(items)
  //println(value.deep.mkString("\n"))

}