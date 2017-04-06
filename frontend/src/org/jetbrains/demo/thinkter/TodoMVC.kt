package org.jetbrains.demo.thinkter

import kotlinx.html.div
import kotlinx.html.h4
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

    fun handleAppendTodoItem(nr: Int) {
        setState { todos = state.todos.slice(0..nr) + listOf("") + state.todos.slice(nr + 1..state.todos.size - 1) }
    }

    fun handleUpdateTodoItem(nr: Int, text: String) {
        setState { todos = state.todos.slice(0..nr - 1) + listOf(text) + state.todos.slice(nr + 1..state.todos.size - 1) }
    }

    fun handleRemoveTodoItem(nr: Int) {
        setState {
            todos =
                    if (state.todos.size <= 1) listOf("")
                    else state.todos.slice(0..nr - 1) + state.todos.slice(nr + 1..state.todos.size - 1)
        }
    }

    override fun ReactDOMBuilder.render() {
        div {
            h4 { +"Todo list:" }
            ol {
                for ((i, t) in state.todos.withIndex())
                    TodoItem {
                        id = i
                        text = t
                        appendTodoItem = this@TodoMVC::handleAppendTodoItem
                        updateTodoItem = this@TodoMVC::handleUpdateTodoItem
                        removeTodoItem = this@TodoMVC::handleRemoveTodoItem
                    }
            }
        }
    }


    class State(var todos: List<String>) : RState
}