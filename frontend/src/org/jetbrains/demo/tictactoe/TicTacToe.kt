package org.jetbrains.demo.tictactoe

import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.js.onClickFunction
import org.jetbrains.firebase.subscribeToTicTacToeState
import org.jetbrains.firebase.writeTicTacToeState
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

class TicTacToe : ReactDOMComponent<TicTacToe.Props, TicTacToe.State>() {
    companion object : ReactComponentSpec<TicTacToe, TicTacToe.Props, TicTacToe.State>

    init {
        state = State(Array(9, { initHistory }), 0)

        subscribeToTicTacToeState(props.id.toString()) {
            setState {
                val strings = it.`val`()
                history = arrayOf(History(strings.map(String::first).toCharArray()))
            }
        }
    }

    val initHistory get() = History(CharArray(9) { ' ' })

    val xIsNext get() = state.history.last().squares.map {
        when (it) {
            'X' -> -1
            'O' -> 1
            else -> 0
        }
    }.sum() > 0

    val details: String get() = if (winner == ' ') {
        "Next player: " + if (xIsNext) 'X' else 'O'
    } else "Winner: " + winner

    val winner: Char get() = calculateWinner(state.history.last().squares)

    override fun ReactDOMBuilder.render() {
        div("game") {
            div("title") {
                +"Board ${props.id}"
            }
            div("board") {
                Board {
                    squares = state.history.last().squares
                    onClick = { handleClick(it) }
                }
            }
            div {
                div("details") {
                    +details
                }
                button {
                    +"reset"
                    onClickFunction = {
                        setState { history = arrayOf(initHistory) }
                        writeTicTacToeState(props.id.toString(), initHistory.squares.map(Char::toString).toTypedArray())
                    }
                }
            }
        }
    }

    private fun handleClick(i: Int) {
        if (winner != ' ') return
        if (state.history.last().squares[i] == ' ') {
            val newHistory = state.history.sliceArray(0..state.stepNumber + 1)
            val current = newHistory.last()
            val squares = current.squares.copyOf()
            squares[i] = if (xIsNext) 'X' else 'O'

            setState {
                history = newHistory.apply { last().squares = squares }
                stepNumber = newHistory.size
            }
            writeTicTacToeState(props.id.toString(), squares.map(Char::toString).toTypedArray())
        }
    }

    private fun calculateWinner(squares: CharArray): Char {
        LINES.forEach { (a, b, c) ->
            if (squares[a] == squares[b] && squares[a] == squares[c]) {
                return squares[a]
            }
        }
        return ' '
    }

    class Props(var id: Int) : RProps()

    class State(var history: Array<History>, var stepNumber: Int) : RState
}


class History(var squares: CharArray)