package org.jetbrains.demo.tictactoe

import kotlinx.html.button
import kotlinx.html.js.onClickFunction
import react.RProps
import react.ReactComponentNoState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class Square : ReactDOMComponent<SquareProps, ReactComponentNoState>() {
    companion object : ReactComponentSpec<Square, SquareProps, ReactComponentNoState>

    override fun ReactDOMBuilder.render() {
        button(classes = "square") {
            +props.value.toString()
            onClickFunction = {
                props.onClick()
            }
        }
    }

    init {
        state = ReactComponentNoState()
    }
}

class SquareProps(var value: Char, var onClick: () -> Unit) : RProps()