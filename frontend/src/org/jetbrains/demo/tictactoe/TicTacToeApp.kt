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
                Square {
                    value = if (state.history[0]) 'X' else 'O'
                    onClick = {
                        setState {
                            history = booleanArrayOf(true, false, false)
                        }
                    }
                }
                Square {
                    value = if (state.history[1]) 'X' else 'O'
                    onClick = {
                        setState {
                            history = booleanArrayOf(false, true, false)
                        }
                    }
                }
                Square {
                    value = if (state.history[2]) 'X' else 'O'
                    onClick = {
                        setState {
                            history = booleanArrayOf(false, false, true)
                        }
                    }
                }
            }
        }
    }

    init {
        state = TicTacToeState(BooleanArray(3))
    }
}

class TicTacToeState(var history: BooleanArray) : RState