package org.brooks

fun combineVertically(noLog: Boolean? = false, vararg strings: String): String {
    var firstSize = 0;
    fun logPredicate(list: List<List<String>>):Boolean {
        return list.any {
            println(it.size.toString() + " " + firstSize)
            return@any it.size != firstSize
        }
    }
    fun predicate(list: List<List<String>>) = list.any { it.size != firstSize }
    val linesList = strings.map { it.trimIndent().lines() }
        .apply {
            firstSize = first().size
            val badNumLines = if(noLog == true) { predicate(this) } else { logPredicate(this) }
            if(badNumLines) { throw IllegalArgumentException("Line Length Error") }
        }
    return (0 until firstSize).joinToString("\n") { i ->
        linesList.joinToString(separator = "") { it[i] }
    }
}
val topCard = """ ____________________________ 
*                            *"""
val bottomCard = "*____________________________*"
val edgeCard = """
 *
 *
 *
 *
 *
 *
 *
"""

val emptySquiggle = """
   .''.   
   \   \  
   /   /  
  /   \   
   \   \  
   /   /  
   '..'   
"""
val medSquiggle = """
  .'''.  
  \. .\  
  . . .  
 . . .   
  . . .  
  /. ./  
  '..'   
"""
val fullSquiggle = """
  .'''. 
  \===\ 
  /===/ 
 /===\  
  \===\ 
  /===/ 
  '..'  
"""
val emptyDiamond = """
    A    
   / \   
  /   \  
 <     > 
  \   /  
   \ /   
    V    
"""
val medDiamond = """
    A    
   . .   
  . . .  
 < . . > 
  . . .  
   . .   
    V    
"""
val fullDiamond = """
   A    
  /=\   
 /===\  
< === > 
 \===/  
  \=/   
   V    
"""
val emptyOval = """
 .-'''-. 
 |     | 
 |     | 
 |     | 
 |     | 
 |     | 
 '-___-' 
"""
val medOval = """
 .-'''-. 
 . . . . 
 . . . . 
 . . . . 
 . . . . 
 . . . . 
 '-___-' 
"""
val fullOval = """
 .-'''-. 
 |=====| 
 |=====| 
 |=====| 
 |=====| 
 |=====| 
 '-___-' 
"""
fun emptySpace() = "\n \n \n \n \n \n \n \n "
val leftCard = combineVertically(noLog = true, edgeCard, emptySpace(), emptySpace())
val rightCard = combineVertically(noLog = true, emptySpace(), emptySpace(), edgeCard)
val redColor = "RED"; val greenColor = "GREEN"; val blueColor = "BLUE"


fun drawCard(): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySquiggle,
        emptySquiggle,
        emptySquiggle,
        emptySpace(),
        rightCard
    )
    return """
$topCard
$combineVertical
$bottomCard
"""
}

fun drawCard2(): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        medDiamond,
        medDiamond,
        medDiamond,
        emptySpace(),
        rightCard
    )
    return """
$topCard
$combineVertical
$bottomCard
"""
}

fun drawCard3(): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        fullOval,
        fullOval,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        rightCard
    )
    return """
$topCard
$combineVertical
$bottomCard
"""
}


fun main(){
    print(drawCard())
    print(drawCard2())
    print(drawCard3())
}