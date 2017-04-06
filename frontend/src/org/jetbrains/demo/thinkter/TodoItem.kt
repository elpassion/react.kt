package org.jetbrains.demo.thinkter

import kotlinx.html.*
import react.*
import react.dom.*

class TodoItem : ReactDOMComponent<TodoItem.Props, ReactComponentNoState>() {
    companion object : ReactComponentSpec<TodoItem, Props, ReactComponentNoState>

    init {
        state = ReactComponentNoState()
    }

    override fun ReactDOMBuilder.render() {
        li { +props.todo }
    }

    class Props(var todo: String, var updateTodo: (Int, String) -> Unit = {nr, text -> }) : RProps()
}