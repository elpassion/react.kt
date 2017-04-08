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

class TicTacToe : ReactDOMComponent<TicTacToe.Props, TicTacToe.State>() {
    companion object : ReactComponentSpec<TicTacToe, TicTacToe.Props, TicTacToe.State>

    init {
        state = State(initSquares)

        subscribeToTicTacToeState(props.id.toString()) {
            setState {
                squares = it.`val`() ?: initSquares
            }
        }
    }

    val initSquares get() = Array(9) { " " }

    val xIsNext get() = state.squares.map {
        when (it) {
            "X" -> -1
            "O" -> 1
            else -> 0
        }
    }.sum() > 0

    val details get() = if (winner == " ") {
        "Next player: " + if (xIsNext) 'X' else 'O'
    } else "Winner: " + winner

    val winner: String get() = calculateWinner(state.squares)

    override fun ReactDOMBuilder.render() {
        div("game") {
            div("title") {
                +"Board ${props.id}"
            }
            div("board") {
                Board {
                    squares = state.squares
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
                        setState { squares = initSquares }
                        writeTicTacToeState(props.id.toString(), initSquares)
                    }
                }
            }
        }
    }

    private fun handleClick(i: Int) {
        if (winner != " ") return
        val newSquares = state.squares.copyOf()
        if (newSquares[i] == " ") {
            newSquares[i] = if (xIsNext) "X" else "O"
            setState { squares = newSquares }
            writeTicTacToeState(props.id.toString(), newSquares)
        }
    }

    private fun calculateWinner(squares: Array<String>): String {
        val WINNING_LINES = arrayOf(
                intArrayOf(0, 1, 2),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8),
                intArrayOf(0, 3, 6),
                intArrayOf(1, 4, 7),
                intArrayOf(2, 5, 8),
                intArrayOf(0, 4, 8),
                intArrayOf(2, 4, 6)
        )
        for ((a, b, c) in WINNING_LINES)
            if (squares[a] != " " && squares[a] == squares[b] && squares[a] == squares[c])
                return squares[a]
        return " "
    }


    class Props(var id: Int) : RProps()

    class State(var squares: Array<String>) : RState
}
