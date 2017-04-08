package org.jetbrains.demo.tictactoe

import kotlinx.html.button
import kotlinx.html.js.onClickFunction
import react.RProps
import react.ReactComponentNoState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class Square : ReactDOMComponent<Square.Props, ReactComponentNoState>() {
    companion object : ReactComponentSpec<Square, Props, ReactComponentNoState>

    init {
        state = ReactComponentNoState()
    }

    override fun ReactDOMBuilder.render() {
        button(classes = "square") {
            +props.value
            onClickFunction = { props.onClick() }
        }
    }

    class Props(var value: String, var onClick: () -> Unit) : RProps()
}

