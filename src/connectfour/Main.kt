package connectfour

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("Connect Four")

    println("First player's name:")
    val playerOne = scanner.nextLine()

    println("Second player's name:")
    val playerTwo = scanner.nextLine()

    var boardSize = Pair(0, 0)
    do {
        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        val boardSizeStr = scanner.nextLine().lowercase()

        if (boardSizeStr == "") {
            boardSize = Pair(6, 7)
            continue
        }

        if (!boardSizeStr.contains("x")) {
            println("Invalid input")
            continue
        }

        boardSizeStr.split("x").let{
            val candidate = Pair(it[0].trim().toIntOrNull(), it[1].trim().toIntOrNull())
            if (candidate.first == null || candidate.second == null) {
                println("Invalid input")
            } else if (candidate.first !in 5..9) {
                println("Board rows should be from 5 to 9")
            } else if (candidate.second !in 5..9) {
                println("Board columns should be from 5 to 9")
            } else {
                boardSize = candidate as Pair<Int, Int>
            }
        }

    } while (boardSize.first == 0)

    println("$playerOne VS $playerTwo")
    println("${boardSize.first} X ${boardSize.second} board")
}