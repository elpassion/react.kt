package org.jetbrains.demo.todomvc

import kotlinx.html.*
import org.jetbrains.firebase.subscribeToTodoListState
import org.jetbrains.firebase.writeTodoListState
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class TodoMVC : ReactDOMComponent<TodoMVC.Props, TodoMVC.State>() {
    companion object : ReactComponentSpec<TodoMVC, Props, State>

    init {
        state = State(listOf("eat", "write code", "eat some more", "write more code", "sleep", "repeat"))
        subscribeToTodoListState(props.id.toString()) {
            println(it)
            setState { todos = it.`val`().toList() }
        }
    }

    fun handleAppendTodoItem(nr: Int) {
        setState { todos = state.todos.slice(0..nr) + listOf("") + state.todos.slice(nr + 1..state.todos.size - 1) }
        writeToFirebase()
    }

    fun handleUpdateTodoItem(nr: Int, text: String) {
        setState { todos = state.todos.slice(0..nr - 1) + listOf(text) + state.todos.slice(nr + 1..state.todos.size - 1) }
        writeToFirebase()
    }

    fun handleRemoveTodoItem(nr: Int) {
        setState {
            todos =
                    if (state.todos.size <= 1) listOf("")
                    else state.todos.slice(0..nr - 1) + state.todos.slice(nr + 1..state.todos.size - 1)
        }
        writeToFirebase()
    }

    val chars get() = state.todos.map { it.size }.sum()

    val status get() = "elements:${state.todos.size} chars:$chars"

    override fun ReactDOMBuilder.render() {
        section("todoapp") {
            div {
                header("header") {
                    h1 { +"todos" }
                }
                section("main") {
                    ul("todo-list") {
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
                footer("footer") { +status }
            }
        }
    }

    private fun writeToFirebase() {
        writeTodoListState(props.id.toString(), state.todos.toTypedArray())
    }

    class Props(var id: Int) : RProps()

    class State(var todos: List<String>) : RState
}