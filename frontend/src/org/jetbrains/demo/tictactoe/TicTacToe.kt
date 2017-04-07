package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import org.jetbrains.firebase.subscribeToTicTacToeState
import org.jetbrains.firebase.subscribeToTodoListState
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

class TicTacToe : ReactDOMComponent<TicTacToeProps, TicTacToeState>() {
    companion object : ReactComponentSpec<TicTacToe, TicTacToeProps, TicTacToeState>

    val details: String get() = if (winner == ' ') {
        "Next player: " + if (state.xIsNext) 'X' else 'O'
    } else "Winner: " + winner

    val winner: Char get() = calculateWinner(state.history.last().squares)

    override fun ReactDOMBuilder.render() {
        //val desc = "Step " + state.stepNumber
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
                /*ol {
                    state.history.mapIndexed { index, history ->
                        li {
                            key = index.toString()
                            a {
                                + desc
                                onClickFunction = {
                                    jumpTo(index)
                                }
                            }
                        }

                    }
                }*/
            }
        }
    }

    init {
        state = TicTacToeState(Array(9, { History(CharArray(9) { ' ' }) }), 0, true)
    }

    private fun handleClick(i: Int) {
        if (winner != ' ') return
        if (state.history.last().squares[i] == ' ') {
            val newHistory = state.history.sliceArray(0..state.stepNumber + 1)
            val current = newHistory.last()
            val squares = current.squares.copyOf()
            squares[i] = if (state.xIsNext) 'X' else 'O'

            setState {
                history = newHistory.apply { last().squares = squares }
                stepNumber = newHistory.size
                xIsNext = !state.xIsNext
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

    private fun jumpTo(step: Int) {
        setState {
            stepNumber = step
            xIsNext = step % 2 != 0
        }
    }
}

class TicTacToeProps(var id: Int) : RProps()

class TicTacToeState(var history: Array<History>, var stepNumber: Int, var xIsNext: Boolean) : RState

class History(var squares: CharArray)