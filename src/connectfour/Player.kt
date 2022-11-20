package connectfour

data class Player(val name: String, val symbol: Char, var score: Int = 0) {

    companion object {
        const val SCORE_WIN = 2
        const val SCORE_DRAW = 1
    }

    fun resetScore() {
        score = 0
    }

    fun scoreWin() {
        score += SCORE_WIN
    }

    fun scoreDraw() {
        score += SCORE_DRAW
    }
}
