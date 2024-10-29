package org.brooks

import org.brooks.ConsoleColor.BLUE
import org.brooks.ConsoleColor.GREEN
import org.brooks.ConsoleColor.RED
import org.brooks.ConsoleColor.WHITE

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

fun getTopCard(c: ColorCode = WHITE) = """$c _______________________________ 
$c*                               *"""
fun getBottomCard(c: ColorCode = WHITE) = "$c*_______________________________*"
fun getEdgeCard(c: ColorCode = WHITE) = """
$c*
$c*
$c*
$c*
$c*
$c*
$c*
"""

fun getEmptySquiggle(c: ColorCode) = """
$c  .''.   
$c  \   \  
$c  /   /  
$c /   \   
$c  \   \  
$c  /   /  
$c  '..'   
"""

fun getMedSquiggle(c: ColorCode) = """
$c  .'''.  
$c  \. .\  
$c  . . .  
$c . . .   
$c  . . .  
$c  /. ./  
$c  '..'   
"""

fun getFullSquiggle(c: ColorCode) = """
$c  .'''. 
$c  \===\ 
$c  /===/ 
$c /===\  
$c  \===\ 
$c  /===/ 
$c  '..'  
"""

fun getEmptyDiamond(c: ColorCode) = """
$c    A    
$c   / \   
$c  /   \  
$c <     > 
$c  \   /  
$c   \ /   
$c    V    
"""

fun getMedDiamond(c: ColorCode) = """
$c    A    
$c   . .   
$c  . . .  
$c < . . > 
$c  . . .  
$c   . .   
$c    V    
"""

fun getFullDiamond(c: ColorCode) = """
$c    A    
$c   /=\   
$c  /===\  
$c < === > 
$c  \===/  
$c   \=/   
$c    V    
"""

fun getEmptyOval(c: ColorCode) = """
$c .-'''-. 
$c |     | 
$c |     | 
$c |     | 
$c |     | 
$c |     | 
$c '-___-' 
"""

fun getMedOval(c: ColorCode) = """
$c .-'''-. 
$c . . . . 
$c . . . . 
$c . . . . 
$c . . . . 
$c . . . . 
$c '-___-' 
"""

fun getFullOval(c: ColorCode) = """
$c .-'''-. 
$c |=====| 
$c |=====| 
$c |=====| 
$c |=====| 
$c |=====| 
$c '-___-' 
"""
fun emptySpace() = "\n \n \n \n \n \n \n \n "
fun emptyLongSpace() = "\n  \n  \n  \n  \n  \n  \n  \n  \n  \n  \n  "
val leftCard = combineVertically(noLog = true, getEdgeCard(), emptySpace(), emptySpace())
val rightCard = combineVertically(noLog = true, emptySpace(), emptySpace(), getEdgeCard())


//SAMPLING CODE
fun drawSquiggleCard3(c:ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        getEmptySquiggle(c),
        getEmptySquiggle(c),
        getEmptySquiggle(c),
        emptySpace(),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}

fun drawDiamondCard3(c:ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        getMedDiamond(c),
        getMedDiamond(c),
        getMedDiamond(c),
        emptySpace(),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}

fun drawOvalCard3(c: ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        getFullOval(c),
        getFullOval(c),
        getFullOval(c),
        emptySpace(),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}
fun dealRow1() = combineVertically(
    true,
    drawSquiggleCard3(RED),
    emptyLongSpace(),
    drawDiamondCard3(RED),
    emptyLongSpace(),
    drawOvalCard3(RED)
)

fun dealRow2() = combineVertically(
    true,
    drawSquiggleCard3(GREEN),
    emptyLongSpace(),
    drawDiamondCard3(GREEN),
    emptyLongSpace(),
    drawOvalCard3(GREEN)
)

fun dealRow3() = combineVertically(
    true,
    drawSquiggleCard3(BLUE),
    emptyLongSpace(),
    drawDiamondCard3(BLUE),
    emptyLongSpace(),
    drawOvalCard3(BLUE)
)

fun dealPlayableCards() = buildString {
    append(dealRow1())
    appendLine()
    append(dealRow2())
    appendLine()
    append(dealRow3())
    appendLine()
}

fun main(){
    print(dealPlayableCards())
}