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
                    squares = CharArray(3)
                    onClick = { handleClick(it) }
                }
            }
        }
    }

    private fun handleClick(i: Int) {
        //TODO: update history
    }

    init {
        state = TicTacToeState(BooleanArray(3))
    }
}

class TicTacToeState(var history: BooleanArray) : RState