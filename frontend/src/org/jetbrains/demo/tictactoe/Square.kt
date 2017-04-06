package org.jetbrains.demo.tictactoe

import kotlinx.html.div
import react.ReactComponentNoProps
import react.ReactComponentNoState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class Square : ReactDOMComponent<ReactComponentNoProps, ReactComponentNoState>() {
    companion object : ReactComponentSpec<Square, ReactComponentNoProps, ReactComponentNoState>

    override fun ReactDOMBuilder.render() {
        div { +"Square" }
    }

    init {
        state = ReactComponentNoState()
    }
}