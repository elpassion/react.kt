package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import react.RProps
import react.ReactComponentNoState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class Board : ReactDOMComponent<Board.Props, ReactComponentNoState>() {
    companion object : ReactComponentSpec<Board, Props, ReactComponentNoState>

    init {
        state = ReactComponentNoState()
    }

    override fun ReactDOMBuilder.render() {
        div {
            for (row in 0..2)
                div("board-row") {
                    for (col in 0..2)
                        renderSquare(row * 3 + col)
                }
        }
    }

    private fun ReactDOMBuilder.renderSquare(i: Int) = Square {
        value = props.squares[i]
        onClick = { props.onClick(i) }
    }

    class Props(var squares: Array<String>, var onClick: (Int) -> Unit) : RProps()
}

