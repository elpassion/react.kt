package org.jetbrains.demo.thinkter

import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.ol
import react.RState
import react.ReactComponentNoProps
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class TodoMVC : ReactDOMComponent<ReactComponentNoProps, TodoMVC.State>() {
    companion object : ReactComponentSpec<TodoMVC, ReactComponentNoProps, State>

    init {
        state = State(listOf("write code", "eat", "sleep", "repeat"))
    }

    override fun ReactDOMBuilder.render() {
        div {
            h3 { +"Todo list:" }
            ol {
                for(t in state.todos)
                    TodoItem { todo = t }
            }
        }
    }


    class State(var todos: List<String>) : RState
}