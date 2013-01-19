object product {
  def product(f: Int => Int) (a: Int, b: Int): Int = {
  	if (a > b) 1 else f(a) * product (f) (a+1,b)
  }                                               //> product: (f: Int => Int)(a: Int, b: Int)Int
  
  product(x => x-1)(3,5)                          //> res0: Int = 24
  
  def factorial(x: Int): Int = product(y => y) (1,x)
                                                  //> factorial: (x: Int)Int
  factorial(4)                                    //> res1: Int = 24
  
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int) (a: Int, b: Int): Int = {
  	if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a+1,b))
  }                                               //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:
                                                  //|  Int)Int
  
  mapReduce(x => x-1, (a,b) => a*b, 1)(2,3)       //> res2: Int = 2
}