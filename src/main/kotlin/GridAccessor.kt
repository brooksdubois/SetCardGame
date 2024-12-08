package org.brooks

class GridAccessor(private val rows: Int, private val cols: Int) {
    private val grid = Array(rows * cols) { "Item-${it + 1}" }

    fun indexOf(row: Char, col: Int): Int {
        val rowIndex = row.lowercaseChar() - 'a'
        require(rowIndex in 0 until rows) { "Invalid row: $row" }
        require(col in 1..cols) { "Invalid column: $col" }
        return rowIndex * cols + (col - 1)
    }

    operator fun get(row: Char, col: Int): String {
        val index = indexOf(row, col)
        return grid[index]
    }
}