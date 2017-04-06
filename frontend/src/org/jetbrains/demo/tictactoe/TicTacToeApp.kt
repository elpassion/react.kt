package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import react.RState
import react.ReactComponentNoProps
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

val LINES = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
)

class TicTacToeApp : ReactDOMComponent<ReactComponentNoProps, TicTacToeState>() {
    companion object : ReactComponentSpec<TicTacToeApp, ReactComponentNoProps, TicTacToeState>

    override fun ReactDOMBuilder.render() {
        div("game") {
            div("title") {
                +"Tic Tac Toe"
            }
            div("board") {
                Board {
                    squares = state.history.last().squares
                    onClick = { handleClick(it) }
                }
            }
        }
    }

    private fun handleClick(i: Int) {
        if (state.history.last().squares[i] == ' ') {
            val history = state.history.sliceArray(0..state.stepNumber + 1)
            val current = history.last()
            val squares = current.squares.copyOf()
            squares[i] = if (state.xIsNext) 'X' else 'O'
            setState {
                this.history = history.apply { last().squares = squares }
                stepNumber = history.size
                xIsNext = !state.xIsNext
            }
            render()
            val result = calculateWinner(current.squares)
            if (result == null) {
                val next = if (state.xIsNext) 'O' else 'X'
                println("Next player: " + next)
            } else {
                println("Winner: " + result)
                //TODO: End game
            }
        }
    }

    init {
        state = TicTacToeState(Array(9, { History(charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')) }), 0, true)
    }
}

fun calculateWinner(squares: CharArray): Char? {
    LINES.forEach { (a, b, c) ->
        if (squares[a] == squares[b] && squares[a] == squares[c]) {
            return squares[a]
        }
    }
    return null
}

class TicTacToeState(var history: Array<History>, var stepNumber: Int, var xIsNext: Boolean) : RState

class History(var squares: CharArray)