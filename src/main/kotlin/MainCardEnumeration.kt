package org.brooks



const val sep = ","
const val endLine = "|"

typealias CardFaceValue = String
typealias Shape = CardFaceValue
class Shapes {
    companion object{
        const val Squiggle: Shape = "S"
        const val Oval: Shape = "O"
        const val Diamond: Shape = "D"

        fun getShapes():List<Shape> =
            listOf(Oval, Diamond, Squiggle)
    }

}

typealias Fill = CardFaceValue
class Fills {
    companion object{
        const val Light:Fill = "L"
        const val Shaded:Fill = "S"
        const val Full:Fill = "F"

        fun getFills(): List<CardFaceValue>
        = listOf(Full, Shaded, Light)
    }

    fun isFill(input: String)
        = if(input == Full || input == Shaded || input == Light)
            true else false

}

typealias Color = CardFaceValue
class Colors {
    companion object{
        const val Red:Color = "R"
        const val Green:Color = "G"
        const val Blue:Color = "B"

        fun getColors(): List<CardFaceValue>
            = listOf(Red, Green, Blue)
    }
}

typealias Count = CardFaceValue
class Counts {
    companion object{
        const val `1`:Count = "1"
        const val `2`:Count = "2"
        const val `3`:Count = "3"

        fun getCounts(): List<CardFaceValue>
            = listOf(Counts.`1`, Counts.`2`,Counts.`3`)
    }
}

data class Card(val shape: Shape, val color: Color, val fill: Fill, val count: Count)

typealias CardSerialized = String
fun Card.toCardStr():CardSerialized =
    "$endLine$count$sep$color$sep$fill$sep$shape$endLine"

typealias CardIndex = Int
typealias CardHashMap = MutableMap<CardIndex, Pair<Card, CardSerialized>>
fun createHashMap(): CardHashMap {
    val cardHashMap: CardHashMap = mutableMapOf()
    Shapes.getShapes().forEachIndexed { shapeIndex, shape ->
        Counts.getCounts().forEachIndexed { countIndex, count ->
            Fills.getFills().forEachIndexed { filIndex, fill ->
                Colors.getColors().forEachIndexed { colorIndex, color ->
                    val card = Card(shape, color, fill, count)
                    val cardStr = card.toCardStr()
                    val cardIndex = "${shapeIndex+1}${countIndex+1}${filIndex+1}${colorIndex+1}".toInt()
                    cardHashMap[cardIndex] = Pair(card, cardStr)
                }
            }
        }
    }
    return cardHashMap
}

fun CardHashMap.shuffleAndDeal12() = this.entries.shuffled().take(12)


fun main() {
    val dealCards = createHashMap().shuffleAndDeal12()

    repeat(3){outerX ->
        repeat(4){innerY ->
            print(dealCards[outerX * 4 + innerY].value.second)
        }

        if(outerX != 2) println()
    }

}
