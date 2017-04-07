package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import react.RProps
import react.RState
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

class TicTacToeApp : ReactDOMComponent<TicTacToeProps, TicTacToeState>() {
    companion object : ReactComponentSpec<TicTacToeApp, TicTacToeProps, TicTacToeState>

    override fun ReactDOMBuilder.render() {
        div("game") {
            div("title") {
                + "Board ${props.id}"
            }
            div("board") {
                Board {
                    squares = state.history.last().squares
                    onClick = { handleClick(it) }
                }
            }
            div("details") {
                + state.details
            }
        }
    }

    private fun handleClick(i: Int) {
        if (state.history.last().squares[i] == ' ') {
            val history = state.history.sliceArray(0..state.stepNumber + 1)
            val current = history.last()
            val squares = current.squares.copyOf()
            squares[i] = if (state.xIsNext) 'X' else 'O'
            val result = calculateWinner(current.squares)
            val log = if (result == ' ') {
                val next = if (state.xIsNext) 'O' else 'X'
                "Next player: " + next
            } else {
                //TODO: End game
                "Winner: " + result
            }
            setState {
                this.history = history.apply { last().squares = squares }
                stepNumber = history.size
                xIsNext = !state.xIsNext
                details = log
            }
            render()
        }
    }

    init {
        state = TicTacToeState(Array(9, { History(CharArray(9) { ' ' }) }), 0, true, "Next player: X")
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

class TicTacToeProps(var id: Int) : RProps()

class TicTacToeState(var history: Array<History>, var stepNumber: Int, var xIsNext: Boolean, var details: String) : RState

class History(var squares: CharArray)