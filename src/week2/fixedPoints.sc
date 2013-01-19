import math.abs

object fixedPoints {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4
  
  def isCloseEnough(x: Double, y: Double) =
  	abs((x-y)/x)/x < tolerance                //> isCloseEnough: (x: Double, y: Double)Boolean
  	
  	
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  	def iterate(guess: Double): Double = {
  		val next = f(guess)
  		if (isCloseEnough(guess, next)) next
  		else iterate (next)
  	}
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Unit
  
  def sqrt(x: Double) = fixedPoint(y =>( y+x/y)/2)(1.0)
                                                  //> sqrt: (x: Double)Unit
  sqrt(3)
}