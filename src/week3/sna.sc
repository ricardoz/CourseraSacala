object sna {
  
  def averageSteps(x: Int): Int = {
  	val networkSize = x
  
  	val middle = ( networkSize + 1 )/ 2
  
  	val networkWidth = networkSize - middle
  
  	def stepsToInfection(node: Int): Int = math.abs(node - middle)
  	
  	def cumSteps(x: Int, accu: Int): Int = if (x > networkSize) accu else cumSteps(x+1, accu + stepsToInfection(x))
  	
  	for (i <- 1 until networkSize){
  		print(i + ": " + stepsToInfection(i))
  	}
  	
  	cumSteps(1, 0)/x
  }                                               //> averageSteps: (x: Int)Int
  
  averageSteps(101)                               //> 1: 502: 493: 484: 475: 466: 457: 448: 439: 4210: 4111: 4012: 3913: 3814: 371
                                                  //| 5: 3616: 3517: 3418: 3319: 3220: 3121: 3022: 2923: 2824: 2725: 2626: 2527: 2
                                                  //| 428: 2329: 2230: 2131: 2032: 1933: 1834: 1735: 1636: 1537: 1438: 1339: 1240:
                                                  //|  1141: 1042: 943: 844: 745: 646: 547: 448: 349: 250: 151: 052: 153: 254: 355
                                                  //| : 456: 557: 658: 759: 860: 961: 1062: 1163: 1264: 1365: 1466: 1567: 1668: 17
                                                  //| 69: 1870: 1971: 2072: 2173: 2274: 2375: 2476: 2577: 2678: 2779: 2880: 2981: 
                                                  //| 3082: 3183: 3284: 3385: 3486: 3587: 3688: 3789: 3890: 3991: 4092: 4193: 4294
                                                  //| : 4395: 4496: 4597: 4698: 4799: 48100: 49res0: Int = 25
}