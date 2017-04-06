package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import react.RProps
import react.ReactComponentNoState
import react.ReactComponentSpec
import react.ReactElement
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class Board : ReactDOMComponent<BoardProps, ReactComponentNoState>() {
    companion object : ReactComponentSpec<Board, BoardProps, ReactComponentNoState>

    override fun ReactDOMBuilder.render() {
        div {
            div("board-row") {
                renderSquare(0)
                renderSquare(1)
                renderSquare(2)
            }
            div("board-row") {
                renderSquare(3)
                renderSquare(4)
                renderSquare(5)
            }
            div("board-row") {
                renderSquare(6)
                renderSquare(7)
                renderSquare(8)
            }
        }
    }

    private fun ReactDOMBuilder.renderSquare(i: Int): ReactElement =
            Square {
                value = props.squares[i]
                onClick = {
                    props.onClick(i)
                }
            }

    init {
        state = ReactComponentNoState()
    }
}

class BoardProps(var squares: CharArray, var onClick: (Int) -> Unit) : RProps()