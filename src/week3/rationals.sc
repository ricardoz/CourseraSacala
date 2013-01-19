object rationals {
 	val x = new Rational(1,3)                 //> x  : Rational = 1/3
 	val y =  new Rational(5,7)                //> y  : Rational = 5/7
 	val z = new Rational(3,2)                 //> z  : Rational = 3/2
  x.add(y)                                        //> res0: Rational = 22/21
  -x                                              //> res1: Rational = 1/-3
  x - y -z                                        //> res2: Rational = -79/42
  x < y                                           //> res3: Boolean = true
  
}

class Rational(x: Int, y:Int){
	require(y != 0, "denom greater than 0")

  private def gcd(a: Int, b: Int): Int = if(b==0) a else gcd (b, a%b)
  private val g = gcd (x,y)
	def numer = x/g
	def denom = y/g
	
	def add(that: Rational) =
		new Rational(
			numer * that.denom + denom * that.numer,
			denom * that.denom
		)
		
	def <(that: Rational) = numer * that.denom < that.numer * denom
	
	def max(that: Rational) = if (this < that) that else this
	 
		
	def unary_- : Rational =
		new Rational(
			-numer,
			denom
		)
		
	def - (that: Rational) =
		this.add(-that)
		
	override def toString = numer + "/" + denom
	
}