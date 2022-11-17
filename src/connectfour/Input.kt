package connectfour

import java.util.*

fun getPlayerName(scanner: Scanner, query: String): String {
    println(query)
    return scanner.nextLine()
}

fun getBoardSize(scanner: Scanner): Pair<Int, Int> {
    var boardSize = Pair(0, 0)
    do {
        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        val boardSizeStr = scanner.nextLine().lowercase()

        if (boardSizeStr == "") {
            boardSize = Pair(7, 6)
            continue
        }

        if (!boardSizeStr.contains("x")) {
            println("Invalid input")
            continue
        }

        boardSizeStr.split("x").let{
            val candidate = Pair(it[1].trim().toIntOrNull(), it[0].trim().toIntOrNull())
            if (candidate.first == null || candidate.second == null) {
                println("Invalid input")
            } else if (candidate.second !in 5..9) {
                println("Board rows should be from 5 to 9")
            } else if (candidate.first !in 5..9) {
                println("Board columns should be from 5 to 9")
            } else {
                boardSize = candidate as Pair<Int, Int>
            }
        }

    } while (boardSize.first == 0)
    return boardSize
}

fun getMove(currentPlayer: Pair<String, Char>, gameBoard: MutableList<MutableList<Char>>): Int {
    var value: Int = -1
    do {
        println("${currentPlayer.first}'s turn:")

        val input = readln()
        if (input == "end") {
            value = 0
        } else {
            with(input.trim().toIntOrNull()) {
                if (this == null) {
                    println("Incorrect column number")
                } else if (this !in (1..gameBoard.size)) {
                    println("The column number is out of range (1 - ${gameBoard.size})")
                } else if (gameBoard[this - 1][gameBoard[this - 1].lastIndex] != EMPTY_FIELD) {
                    println("Column $this is full")
                } else {
                    value = this
                }
            }
        }
    } while (value == -1)
    return value
}
