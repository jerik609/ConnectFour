package connectfour

class Game(private var numberOfGames: Int, private val playerOne: Player, private val playerTwo: Player) {

    private val singleGame: Boolean = numberOfGames == 1
    private val initialNumOfGames = numberOfGames

    fun getPlayerOne(): Player {
        return playerOne
    }

    fun getPlayerTwo(): Player {
        return playerTwo
    }

    fun decreaseGameCount(): Int {
        if (!singleGame) println("Score\n${playerOne.name}: ${playerOne.score} ${playerTwo.name}: ${playerTwo.score}")
        return (--numberOfGames).also { if (it > 0) println("Game #${currentGameNo()}") }
    }

    fun gamesLeft(): Int {
        return numberOfGames
    }

    fun currentGameNo(): Int {
        return initialNumOfGames - numberOfGames + 1
    }
}
