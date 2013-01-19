object week6 {
  val xs = Array(1,2,3,44)                        //> xs  : Array[Int] = Array(1, 2, 3, 44)
  xs map (_ * 2)                                  //> res0: Array[Int] = Array(2, 4, 6, 88)
  
  val s = "Hello World"                           //> s  : java.lang.String = Hello World
  s filter (_.isUpper)                            //> res1: String = HW
  1 to 13 by 3                                    //> res2: scala.collection.immutable.Range = Range(1, 4, 7, 10, 13)
  1 until 13 by 3                                 //> res3: scala.collection.immutable.Range = Range(1, 4, 7, 10)
  
  s exists (_.isUpper)                            //> res4: Boolean = true
  s forall (_.isUpper)                            //> res5: Boolean = false
  
  val z = List(1,2,3,4) zip s                     //> z  : List[(Int, Char)] = List((1,H), (2,e), (3,l), (4,l))
  z.unzip                                         //> res6: (List[Int], List[Char]) = (List(1, 2, 3, 4),List(H, e, l, l))
  
  
  val n=7                                         //> n  : Int = 7
  
  ((1 until n) map (i => (1 until i) map (j => (i,j)))).flatten
                                                  //> res7: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,
                                                  //| 3), (6,4), (6,5))
  
}