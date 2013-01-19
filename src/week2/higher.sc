object higher {
  def sumIntsBetween(a: Int, b: Int): Int = {
  	if (a > b) 0 else a + sumIntsBetween(a+1, b)
  }                                               //> sumIntsBetween: (a: Int, b: Int)Int
  
  def cube(x: Int): Int = {
  	x*x*x
  }                                               //> cube: (x: Int)Int
  
  def sumCubesBetween(a: Int, b: Int): Int = {
  	if (a > b) 1 else a * sumCubesBetween(a+1, b)
  }                                               //> sumCubesBetween: (a: Int, b: Int)Int
  
  def sum(f: Int => Int): (Int, Int) => Int = {
   def sumF(a:Int, b: Int): Int = {
   	if (a < b) 0 else
  	 f(a) + sumF(a+1, b)
   }
  	sumF
  }                                               //> sum: (f: Int => Int)(Int, Int) => Int
  
  def sum2(f: Int => Int) (a: Int, b: Int): Int = {
  	if ( a > b) 0 else (f(a) + sum2(f)(a+1,b))
  }                                               //> sum2: (f: Int => Int)(a: Int, b: Int)Int
  
  def id(x: Int): Int = x                         //> id: (x: Int)Int
  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x-1)
                                                  //> fact: (x: Int)Int
  
  def sumInts = sum(id)                           //> sumInts: => (Int, Int) => Int
  def sumCubes= sum(cube)                         //> sumCubes: => (Int, Int) => Int
  def sumFactorial = sum(fact)                    //> sumFactorial: => (Int, Int) => Int
}