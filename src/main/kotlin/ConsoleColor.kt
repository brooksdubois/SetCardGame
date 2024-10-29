package org.brooks

typealias ColorCode = String
object ConsoleColor {
    const val RESET: ColorCode = "\u001B[0m"
    const val BLACK: ColorCode = "\u001B[30m"
    const val RED: ColorCode = "\u001B[31m"
    const val GREEN: ColorCode = "\u001B[32m"
    const val YELLOW: ColorCode = "\u001B[33m"
    const val BLUE: ColorCode = "\u001B[34m"
    const val PURPLE: ColorCode = "\u001B[35m"
    const val CYAN: ColorCode = "\u001B[36m"
    const val WHITE: ColorCode = "\u001B[37m"

}

//
//const val BLACK_BACKGROUND = "\u001B[40m"
//const val RED_BACKGROUND = "\u001B[41m"
//const val GREEN_BACKGROUND = "\u001B[42m"
//const val YELLOW_BACKGROUND = "\u001B[43m"
//const val BLUE_BACKGROUND = "\u001B[44m"
//const val PURPLE_BACKGROUND = "\u001B[45m"
//const val CYAN_BACKGROUND = "\u001B[46m"
//const val WHITE_BACKGROUND = "\u001B[47m"
//
//const val BOLD = "\u001B[1m"
//const val UNDERLINE = "\u001B[4m"