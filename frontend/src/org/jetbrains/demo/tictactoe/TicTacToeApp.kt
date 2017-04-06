package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import react.RState
import react.ReactComponentNoProps
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

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
        val history = state.history.sliceArray(0..state.stepNumber + 1)
        val current = history.last()
        val squares = current.squares.copyOf()
        //TODO: calculate winner
        squares[i] = if (state.xIsNext) 'X' else 'O'
        setState {
            this.history = history.apply { last().squares = squares }
            stepNumber = history.size
            xIsNext = !state.xIsNext
        }
        render()
    }

    init {
        state = TicTacToeState(Array(9, { History(charArrayOf()) }), 0, true)
    }
}

class TicTacToeState(var history: Array<History>, var stepNumber: Int, var xIsNext: Boolean) : RState

class History(var squares: CharArray)