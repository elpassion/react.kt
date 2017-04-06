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
            +"Tic Tac Toe"
            Square {}
        }
    }

    init {
        state = TicTacToeState()
    }
}

class TicTacToeState : RState