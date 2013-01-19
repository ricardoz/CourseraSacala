package algo1

import scala.io.Source

object Heap {
  
  class Job(w: Int, l: Int) extends Ordered[Job]{
    val ratio = w-l//w*100000/l
    val length = l
    val weight = w
    
    def compare(that: Job): Int = {
      val ratioDiff = this.ratio - that.ratio
      if (ratioDiff == 0){
        this.length - that.length
      } else {
        //print(ratioDiff.toInt)
        ratioDiff
      }
    }
    
    override def toString(): String = {
      "weight = " + weight + 
      " length = " + length + 
      " ratio = " + ratio
    }
    
  }
  
  def calculateJobTotal(list: List[Job], lengthSoFar: Long, weightSoFar: Long): Long = list match {
    
      case Nil => weightSoFar
      case head :: Nil => weightSoFar + ((lengthSoFar + head.length) * head.weight)
      case head :: tail => {
        val lsf = lengthSoFar + head.length
        val wsf = weightSoFar + lsf * head.weight
        //println(wsf)
        calculateJobTotal(list.tail, lsf, wsf)
      }
    
  }

  def main(args: Array[String]): Unit = {
    val in = Source.fromURL("http://spark-public.s3.amazonaws.com/algo2/datasets/jobs.txt").getLines.toList
    
    //while(in.hasNext)
    
    val size = Integer.parseInt(in.head)
    
    //def comparison(x: Int, y: Int): Boolean = {
      
    
    
   
    
    println ("size " + size + "  size " + in.tail.size)
    
    val jobList = for {
      line <- in.tail
      s = line.split("\\s+")
      weight = Integer.parseInt(s(0))
      length = Integer.parseInt(s(1))
    } yield (new Job(weight,length))
    
    val sortedJobList = jobList.sortWith ((a,b) => a > b)
    println(sortedJobList)
    println(calculateJobTotal(sortedJobList, 0, 0))
    
    
    	
  }

}