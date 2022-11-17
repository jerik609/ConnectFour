package connectfour

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("Connect Four")

    val playerOne = getPlayerName(scanner, "First player's name:")
    val playerTwo = getPlayerName(scanner, "Second player's name:")
    val boardSize = getBoardSize(scanner)

    // for developer's convenience, the board is represented as (column, row) internally
    val gameBoard = MutableList(boardSize.first) { MutableList(boardSize.second) { EMPTY_FIELD } }

    playTheGame(Pair(playerOne, PLAYER_ONE_SYMBOL), Pair(playerTwo, PLAYER_TWO_SYMBOL), gameBoard)
}
