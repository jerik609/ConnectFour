package connectfour

fun printBoard(gameBoard: MutableList<MutableList<Char>>,) {
    // print numbers on top
    for (column in 1..gameBoard.size) {
        print(" $column")
    }
    println()

    // print actual board
    for (row in gameBoard[0].size downTo 1)
    {
        for (column in 1..gameBoard.size) {
            print("║")
            print(gameBoard[column - 1][row - 1])
            if (column == gameBoard.size) print('║')
        }
        println()
    }

    // print bottom
    for (column in 1..gameBoard.size) {
        if (column == 1) {
            print("╚═")
        }
        else {
            print("╩═")
            if (column == gameBoard.size) print("╝")
        }
    }

    println()
}

fun displayGameState(
    playerOne: Player,
    playerTwo: Player,
    game: Game,
    gameBoard: MutableList<MutableList<Char>>,
) {
    println("${playerOne.name} VS ${playerTwo.name}")
    println("${gameBoard[0].size} X ${gameBoard.size} board")
    println (if (game.gamesLeft() == 1) "Single game" else "Total ${game.gamesLeft()} games\nGame #${game.currentGameNo()}" )
    printBoard(gameBoard)
}
