package connectfour

import java.util.Scanner

const val PLAYER_SYMBOL_O = 'o'
const val PLAYER_SYMBOL_ASTERISK = '*'

fun main() {
    val scanner = Scanner(System.`in`)

    println("Connect Four")

    val playerOne = Player(getPlayerName(scanner, "First player's name:"), PLAYER_SYMBOL_O)
    val playerTwo = Player(getPlayerName(scanner, "Second player's name:"), PLAYER_SYMBOL_ASTERISK)

    val boardSize = getBoardSize(scanner)
    val numberOfGames = getNumberOfGames(scanner)

    // for developer's convenience, the board is represented as (column, row) internally
    val gameBoard = MutableList(boardSize.first) { MutableList(boardSize.second) { EMPTY_FIELD } }

    playTheGame(Game(numberOfGames, playerOne, playerTwo), gameBoard)
}
