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
    playerOne: Pair<String, Char>,
    playerTwo: Pair<String, Char>,
    gameBoard: MutableList<MutableList<Char>>,
) {
    println("${playerOne.first} VS ${playerTwo.first}")
    println("${gameBoard[0].size} X ${gameBoard.size} board")
    printBoard(gameBoard)
}
