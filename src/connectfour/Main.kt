package connectfour

import java.util.Scanner

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
    return boardSize
}

fun printBoard(rows: Int, columns: Int) {
    // print numbers on top
    for (column in 1..columns) {
        print(" $column")
    }
    println()

    // print actual board
    for (row in 1..rows)
    {
        for (column in 1..columns) {
            print("║ ")
            if (column == columns) print('║')
        }
        println()
    }

    // print bottom
    for (column in 1..columns) {
        if (column == 1) {
            print("╚═")
        }
        else {
            print("╩═")
            if (column == columns) print("╝")
        }
    }
}

fun displayGameState(playerOne: String, playerTwo: String, boardSize: Pair<Int, Int>) {
    println("$playerOne VS $playerTwo")
    println("${boardSize.first} X ${boardSize.second} board")
    printBoard(boardSize.first, boardSize.second)
}

fun main() {
    val scanner = Scanner(System.`in`)

    println("Connect Four")

    val playerOne = getPlayerName(scanner, "First player's name:")
    val playerTwo = getPlayerName(scanner, "Second player's name:")
    val boardSize = getBoardSize(scanner)

    displayGameState(playerOne, playerTwo, boardSize)
}
