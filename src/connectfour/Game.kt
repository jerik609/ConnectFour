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

fun makeMove(currentPlayer: Pair<String, Char>, move: Int, gameBoard: MutableList<MutableList<Char>>): Pair<Int, Int> {
    with(gameBoard[move - 1].indexOfFirst { it == EMPTY_FIELD }) {
        // println("adding ${currentPlayer.second} to position column $move and row $this")
        gameBoard[move - 1][this] = currentPlayer.second
        return Pair(move - 1, this)
    }
}

fun check4(
    currentPlayer: Pair<String, Char>,
    column: Int,
    row: Int,
    stepColumn: Int,
    stepRow: Int,
    gameBoard: MutableList<MutableList<Char>>
): Boolean {
    var count = 0
    var c = column
    var r = row
    for (i in 1..4) {
        if (gameBoard[c][r] != currentPlayer.second) {
            break // other player's disc, sequence broken, can stop checking in this direction
        }
        if (gameBoard[c][r] == currentPlayer.second) {
            // print(gameBoard[c][r]) // debug
            count++ // sequence continues, increase counter
        }
        c += stepColumn
        r += stepRow
        if (c !in 0..gameBoard.lastIndex || r !in 0..gameBoard[0].lastIndex) {
            break // edge of board reached, can stop checking in this direction
        }
    }
    // println() // debug
    return count == 4
}

fun isBoardFull(gameBoard: MutableList<MutableList<Char>>): Boolean {
    for (i in 0..gameBoard.lastIndex) {
        if (gameBoard[i].last() == ' ') {
            return false
        }
    }
    return true
}

/**
 * @param currentPlayer player whose move it is now
 * @param lastMove coordinates of the last move made by current player
 * @param gameBoard the status of the game
 * @return true if current player has won
 */
fun getGameResult(
    currentPlayer: Pair<String, Char>,
    lastMove: Pair<Int, Int>,
    gameBoard: MutableList<MutableList<Char>>
): Int = if (
        check4(currentPlayer, lastMove.first, lastMove.second, 1, 0, gameBoard) ||   // column +
        check4(currentPlayer, lastMove.first, lastMove.second, -1, 0, gameBoard) ||  // column -
        check4(currentPlayer, lastMove.first, lastMove.second, 0, 1, gameBoard) ||   // row +
        check4(currentPlayer, lastMove.first, lastMove.second, 0, -1, gameBoard) ||  // row -
        check4(currentPlayer, lastMove.first, lastMove.second, 1, 1, gameBoard) ||   // diagonal ++
        check4(currentPlayer, lastMove.first, lastMove.second, -1, -1, gameBoard) || // diagonal --
        check4(currentPlayer, lastMove.first, lastMove.second, 1, -1, gameBoard) ||  // diagonal +-
        check4(currentPlayer, lastMove.first, lastMove.second, -1, 1, gameBoard)     // diagonal -+
    ) {
        1
    } else {
        if (isBoardFull(gameBoard)) {
            0
        } else {
            -1
        }
    }

fun playTheGame(
    playerOne: Pair<String, Char>,
    playerTwo: Pair<String, Char>,
    gameBoard: MutableList<MutableList<Char>>,
) {
    var currentPlayer = switchPlayer(Pair("", ' '), playerOne, playerTwo)
    displayGameState(playerOne, playerTwo, gameBoard)

    do {
        // make move or end
        val move = getMove(currentPlayer, gameBoard)
        if (move == 0) {
            break
        }

        // perform the move and display new situation
        val lastMove = makeMove(currentPlayer, move, gameBoard)
        printBoard(gameBoard)

        // evaluate game end
        when (getGameResult(currentPlayer, lastMove, gameBoard)) {
            1 -> {
                println("Player ${currentPlayer.first} won")
                break
            }
            0 -> {
                println("It is a draw")
                break
            }
            else -> currentPlayer = switchPlayer(currentPlayer, playerOne, playerTwo)
        }

    } while (true)
    println("Game Over!")
}
