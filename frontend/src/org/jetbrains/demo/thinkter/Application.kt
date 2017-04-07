package org.jetbrains.demo.thinkter

import kotlinx.html.*
import org.jetbrains.demo.tictactoe.TicTacToe
import org.jetbrains.demo.todomvc.TodoMVC
import react.ReactComponentNoProps
import react.ReactComponentNoState
import react.ReactComponentSpec
import react.dom.ReactDOM
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent
import react.dom.render
import kotlin.browser.document

fun main(args: Array<String>) {
    ReactDOM.render(document.getElementById("content")) {
        Application { }
    }
}

class Application : ReactDOMComponent<ReactComponentNoProps, ReactComponentNoState>() {
    companion object : ReactComponentSpec<Application, ReactComponentNoProps, ReactComponentNoState>

    override fun ReactDOMBuilder.render() {
        div {
            h2("tic") {
                +"Tic Tac Toe"
            }
            table {
                tbody {
                    tr {
                        td("tic") { TicTacToe { id = 1; uuid = generateUUID() } }
                        td("tic") { TicTacToe { id = 2; uuid = generateUUID() } }
                        td("tic") { TicTacToe { id = 3; uuid = generateUUID() } }
                    }
                    tr {
                        td("tic") { TicTacToe { id = 4; uuid = generateUUID() } }
                        td("tic") { TicTacToe { id = 5; uuid = generateUUID() } }
                        td("tic") { TicTacToe { id = 6; uuid = generateUUID() } }
                    }
                }
            }
            TodoMVC { uuid = generateUUID() }
            TodoMVC { uuid = generateUUID() }
        }
    }

    init {
        state = ReactComponentNoState()
    }
}

external fun generateUUID(): String