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

const val BG_COLOR = WHITE


fun emptySpace() = "\n`\n`\n`\n`\n`\n`\n`\n"
fun emptyLongSpace() = "\n  \n  \n  \n  \n  \n  \n  \n  \n  \n  \n  "

val leftCard = combineVertically(noLog = true, getEdgeCard(), emptySpace(), emptySpace(), emptySpace())
val rightCard = combineVertically(noLog = true, emptySpace(),  emptySpace(), emptySpace(), getEdgeCard())

fun getTopCard(c: ColorCode = BG_COLOR) = """$c _________________________________ 
$c*                                 *"""
fun getBottomCard(c: ColorCode = BG_COLOR) = "$c*_________________________________*"
fun getEdgeCard(c: ColorCode = BG_COLOR) = """
$c!
$c!
$c!
$c!
$c!
$c!
$c!
"""

fun getEmptySquiggle(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c  .''.   $e
$c  \   \  $e
$c  /   /  $e
$c /   \   $e
$c  \   \  $e
$c  /   /  $e
$c  '..'   $e
"""
fun getMedSquiggle(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c  .'''.  $e
$c  \. .\  $e
$c  . . .  $e
$c . . .   $e
$c  . . .  $e
$c  /. ./  $e
$c  '..'   $e
"""
fun getFullSquiggle(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c  .'''.  $e
$c  \===\  $e
$c  /===/  $e
$c /===\   $e
$c  \===\  $e
$c  /===/  $e
$c  '..'   $e
"""
fun getEmptyDiamond(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c    A    $e
$c   / \   $e
$c  /   \  $e
$c <     > $e
$c  \   /  $e
$c   \ /   $e
$c    V    $e
"""
fun getMedDiamond(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c    A    $e
$c   . .   $e
$c  . . .  $e
$c < . . > $e
$c  . . .  $e
$c   . .   $e
$c    V    $e
"""
fun getFullDiamond(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c    A    $e
$c   /=\   $e
$c  /===\  $e
$c < === > $e
$c  \===/  $e
$c   \=/   $e
$c    V    $e
"""
fun getEmptyOval(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c .-'''-. $e
$c |     | $e
$c |     | $e
$c |     | $e
$c |     | $e
$c |     | $e
$c '-___-' $e
"""
fun getMedOval(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c .-'''-. $e
$c . . . . $e
$c . . . . $e
$c . . . . $e
$c . . . . $e
$c . . . . $e
$c '-___-' $e
"""
fun getFullOval(c: ColorCode, e: ColorCode = BG_COLOR) = """
$c .-'''-. $e
$c |=====| $e
$c |=====| $e
$c |=====| $e
$c |=====| $e
$c |=====| $e
$c '-___-' $e
"""

fun drawSquiggleCard1(c:ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getEmptySquiggle(c),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}
fun drawSquiggleCard2(c:ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getMedSquiggle(c),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getMedSquiggle(c),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}
fun drawSquiggleCard3(c:ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        getFullSquiggle(c),
        getFullSquiggle(c),
        getFullSquiggle(c),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}
fun drawDiamondCard1(c:ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getEmptyDiamond(c),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}
fun drawDiamondCard2(c:ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getMedDiamond(c),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getMedDiamond(c),
        emptySpace(),
        emptySpace(),
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
        getFullDiamond(c),
        getFullDiamond(c),
        getFullDiamond(c),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}
fun drawOvalCard1(c: ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getEmptyOval(c),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        rightCard
    )
    return """
${getTopCard()}
$combineVertical
${getBottomCard()}
"""
}
fun drawOvalCard2(c: ColorCode): String {
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getMedOval(c),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getMedOval(c),
        emptySpace(),
        emptySpace(),
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
        getFullOval(c),
        getFullOval(c),
        getFullOval(c),
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
    drawSquiggleCard1(RED),
    emptyLongSpace(),
    drawDiamondCard1(GREEN),
    emptyLongSpace(),
    drawOvalCard1(BLUE)
)
fun dealRow2() = combineVertically(
    true,
    drawSquiggleCard2(BLUE),
    emptyLongSpace(),
    drawDiamondCard2(RED),
    emptyLongSpace(),
    drawOvalCard2(GREEN)
)
fun dealRow3() = combineVertically(
    true,
    drawSquiggleCard3(GREEN),
    emptyLongSpace(),
    drawDiamondCard3(BLUE),
    emptyLongSpace(),
    drawOvalCard3(RED)
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