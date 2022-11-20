package connectfour

const val EMPTY_FIELD = ' '

fun resetBoard(gameBoard: MutableList<MutableList<Char>>) {
    gameBoard.forEach { it.replaceAll{ EMPTY_FIELD } }
    printBoard(gameBoard)
}

fun switchPlayer(
    currentPlayer: Player?,
    playerOne: Player,
    playerTwo: Player,
): Player {
    return when {
        currentPlayer == null -> playerOne
        currentPlayer.name == playerOne.name -> playerTwo
        else -> playerOne
    }
}

fun makeMove(currentPlayer: Player, move: Int, gameBoard: MutableList<MutableList<Char>>): Pair<Int, Int> {
    with(gameBoard[move - 1].indexOfFirst { it == EMPTY_FIELD }) {
        // println("adding ${currentPlayer.} to position column $move and row $this")
        gameBoard[move - 1][this] = currentPlayer.symbol
        return Pair(move - 1, this)
    }
}

fun check4(
    currentPlayer: Player,
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
        if (gameBoard[c][r] != currentPlayer.symbol) {
            break // other player's disc, sequence broken, can stop checking in this direction
        }
        if (gameBoard[c][r] == currentPlayer.symbol) {
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
    currentPlayer: Player,
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
    game: Game,
    gameBoard: MutableList<MutableList<Char>>,
) {
    var currentPlayer = switchPlayer(null, game.getPlayerOne(), game.getPlayerTwo())
    var gamesLeft = game.gamesLeft()

    displayGameState(game.getPlayerOne(), game.getPlayerTwo(), game, gameBoard)

    do {
        // make move or end
        val move = getMove(currentPlayer, gameBoard)
        if (move == 0) {
            break
        }

        // perform the move and display new situation
        val lastMove = makeMove(currentPlayer, move, gameBoard)
        printBoard(gameBoard)

        with(getGameResult(currentPlayer, lastMove, gameBoard)) {
            if (this == -1) {
                currentPlayer = switchPlayer(currentPlayer, game.getPlayerOne(), game.getPlayerTwo())
            } else {
                if (this == 1) {
                    println("Player ${currentPlayer.name} won")
                    currentPlayer.scoreWin()
                } else {
                    println("It is a draw")
                    game.getPlayerOne().scoreDraw()
                    game.getPlayerTwo().scoreDraw()
                }
                gamesLeft = game.decreaseGameCount()
                if (gamesLeft > 0) resetBoard(gameBoard)
                currentPlayer = switchPlayer(currentPlayer, game.getPlayerOne(), game.getPlayerTwo())
            }
        }

    } while (gamesLeft > 0)
    println("Game Over!")
}
