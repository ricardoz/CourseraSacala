object intsets {
  val tree1 = new NonEmpty(3, new Empty, new Empty)
                                                  //> tree1  : NonEmpty = {.3.}
  val tree2 = tree1 incl 4                        //> tree2  : IntSet = {.3{.4.}}
  
  val tree3 = tree1 union tree2                   //> tree3  : IntSet = {.3{.4.}}
  
  val tree4 = tree2 union tree1                   //> tree4  : IntSet = {.3{.4.}}
}

abstract class IntSet{
	def incl(x: Int): IntSet
	def contains(x:Int): Boolean
	def union(other: IntSet): IntSet
}

class Empty extends IntSet{
	def contains(x: Int): Boolean = false
	
	def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
	
	def union(other: IntSet): IntSet = other
	
	override def toString = "."
}



class NonEmpty(x: Int, l: IntSet, r: IntSet) extends IntSet{
	val value = x
	val left = l
	val right = r

	def incl(x:Int): IntSet =
		if (x < value) new NonEmpty(value, left incl x, right) else
			if (x > value) new NonEmpty(value, left, right incl x) else this
															
	
	def contains(x: Int): Boolean = if (x == value) true else
					if (x < value) left.contains(x) else right.contains(x)
					
	def union(other: IntSet): IntSet = {
		((left union right) union other) incl value
	}
					
	override def toString = "{" +left + value + right + "}"
}