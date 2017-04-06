package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import react.*
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class TicTacToeApp : ReactDOMComponent<ReactComponentNoProps, TicTacToeState>() {
    companion object : ReactComponentSpec<TicTacToeApp, ReactComponentNoProps, TicTacToeState>

    override fun ReactDOMBuilder.render() {
        div("game") {
            +"Tic Tac Toe"
        }
    }

    init {
        state = TicTacToeState()
    }
}

class TicTacToeState : RState