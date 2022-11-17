package connectfour

const val EMPTY_FIELD = ' '
const val PLAYER_ONE_SYMBOL = 'o'
const val PLAYER_TWO_SYMBOL = '*'

fun resetBoard(gameBoard: MutableList<MutableList<Char>>) {
    gameBoard.forEach { it.replaceAll{ EMPTY_FIELD } }
}

fun switchPlayer(
    currentPlayer: Pair<String, Char>,
    playerOne: Pair<String, Char>,
    playerTwo: Pair<String, Char>,
): Pair<String, Char> {
    return if (currentPlayer.first == playerOne.first) {
        playerTwo
    } else {
        playerOne
    }
}

fun makeMove(currentPlayer: Pair<String, Char>, move: Int, gameBoard: MutableList<MutableList<Char>>) {
    with(gameBoard[move - 1].indexOfFirst { it == EMPTY_FIELD }) {
        // println("adding ${currentPlayer.second} to position column $move and row $this")
        gameBoard[move - 1][this] = currentPlayer.second
    }
}

fun hasWon(currentPlayer: Pair<String, Char>, gameBoard: MutableList<MutableList<Char>>): Boolean {
    return false
}

fun playTheGame(
    playerOne: Pair<String, Char>,
    playerTwo: Pair<String, Char>,
    gameBoard: MutableList<MutableList<Char>>,
) {
    var currentPlayer = switchPlayer(Pair("", ' '), playerOne, playerTwo)
    displayGameState(playerOne, playerTwo, gameBoard)

    do {
        val move = getMove(currentPlayer, gameBoard)

        if (move == 0) {
            println("Game over!")
            break
        }

        makeMove(currentPlayer, move, gameBoard)

        printBoard(gameBoard)

        if (hasWon(currentPlayer, gameBoard)) {
            println("")
            break
        }

        currentPlayer = switchPlayer(currentPlayer, playerOne, playerTwo)
    } while (true)


}
