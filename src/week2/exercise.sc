object exercise {
  def factorial(n: Int): Int = {
		def loop(acc: Int, n: Int): Int = {
			if (n == 1) acc else loop(acc * n, n-1)
		}
		
		loop(1, n)
  }                                               //> factorial: (n: Int)Int
  
  factorial(5)                                    //> res0: Int = 120
}