package org.brooks

import org.brooks.ConsoleColor.BLUE
import org.brooks.ConsoleColor.GREEN
import org.brooks.ConsoleColor.RED
import org.brooks.ConsoleColor.WHITE
import org.brooks.FillCodes.EMPTY
import org.brooks.FillCodes.FULL
import org.brooks.FillCodes.MED

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

typealias FillCode = String
object FillCodes {
    const val EMPTY: FillCode = " "
    const val MED: FillCode = "."
    const val FULL: FillCode = "="
}
typealias AsciiShape = String
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

fun getSquiggle(c: ColorCode, f: FillCode, e: ColorCode = BG_COLOR): AsciiShape = """
$c  .''.   $e
$c  \$f$f$f\  $e
$c  /$f$f$f/  $e
$c /$f$f$f\   $e
$c  \$f$f$f\  $e
$c  /$f$f$f/  $e
$c  '..'   $e
"""

fun getDiamond(c: ColorCode, f: FillCode, e: ColorCode = BG_COLOR): AsciiShape = """
$c    A    $e
$c   /$f\   $e
$c  /$f$f$f\  $e
$c <$f$f$f$f$f> $e
$c  \$f$f$f/  $e
$c   \$f/   $e
$c    V    $e
"""

fun getOval(c: ColorCode, f: FillCode, e: ColorCode = BG_COLOR):AsciiShape = """
$c .-'''-. $e
$c |$f$f$f$f$f| $e
$c |$f$f$f$f$f| $e
$c |$f$f$f$f$f| $e
$c |$f$f$f$f$f| $e
$c |$f$f$f$f$f| $e
$c '-___-' $e
"""
fun getShape(shape: Shape, colorCode: ColorCode, fillCode: FillCode) =
    when (shape) {
        Shapes.Squiggle -> getSquiggle(colorCode, fillCode)
        Shapes.Diamond -> getDiamond(colorCode, fillCode)
        Shapes.Oval -> getOval(colorCode, fillCode)
        else -> throw Error("No matching shape code")
    }

fun getColorCode(color: Color) =
    when(color){
        Colors.Red -> RED
        Colors.Blue -> BLUE
        Colors.Green -> GREEN
        else -> throw Error("No matching color code")
    }

fun getFillCode(fill: Fill) =
    when(fill){
        Fills.Full -> FULL
        Fills.Light -> EMPTY
        Fills.Shaded -> MED
        else -> throw Error("No matching color code")
    }
fun drawCard1(shape: Shape, color: Color, fill: Fill): String {
    val colorCode = getColorCode(color)
    val fillCode = getFillCode(fill)
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
        getShape(shape, colorCode, fillCode),
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

fun drawCard2(shape: Shape, color: Color, fill: Fill): String {
    val colorCode = getColorCode(color)
    val fillCode = getFillCode(fill)
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getShape(shape, colorCode, fillCode),
        emptySpace(),
        emptySpace(),
        emptySpace(),
        getShape(shape, colorCode, fillCode),
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
fun drawCard3(shape: Shape, color: Color, fill: Fill): String {
    val colorCode = getColorCode(color)
    val fillCode = getFillCode(fill)
    val combineVertical = combineVertically(
        noLog = true,
        leftCard,
        getShape(shape, colorCode, fillCode),
        getShape(shape, colorCode, fillCode),
        getShape(shape, colorCode, fillCode),
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
    drawCard1(Shapes.Oval, Colors.Red, Fills.Light),
    emptyLongSpace(),
    drawCard1(Shapes.Squiggle, Colors.Green, Fills.Shaded),
    emptyLongSpace(),
    drawCard1(Shapes.Diamond, Colors.Blue, Fills.Full),
)

fun dealRow2() = combineVertically(
    true,
    drawCard2(Shapes.Oval, Colors.Blue, Fills.Light),
    emptyLongSpace(),
    drawCard2(Shapes.Squiggle, Colors.Green, Fills.Shaded),
    emptyLongSpace(),
    drawCard2(Shapes.Diamond, Colors.Red, Fills.Full),
)

fun dealRow3() = combineVertically(
    true,
    drawCard3(Shapes.Oval, Colors.Green, Fills.Light),
    emptyLongSpace(),
    drawCard3(Shapes.Squiggle, Colors.Blue, Fills.Shaded),
    emptyLongSpace(),
    drawCard3(Shapes.Diamond, Colors.Red, Fills.Full),
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