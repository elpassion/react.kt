package org.jetbrains.demo.todomvc

import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.jetbrains.common.inputValue
import react.*
import react.dom.*

class TodoItem : ReactDOMComponent<TodoItem.Props, ReactComponentNoState>() {
    companion object : ReactComponentSpec<TodoItem, Props, ReactComponentNoState>

    init {
        state = ReactComponentNoState()
    }

    override fun ReactDOMBuilder.render() {
        li {
            input(classes = "edit") {
                value = props.text
                onChangeFunction = { props.updateTodoItem(props.id, it.inputValue) }
            }
            button(classes = "destroy") {
                onClickFunction = { props.removeTodoItem(props.id) }
            }
            button(classes = "destroy add") {
                onClickFunction = { props.appendTodoItem(props.id) }
            }
        }
    }

    class Props(
            var id: Int,
            var text: String,
            var appendTodoItem: (Int) -> Unit = { },
            var updateTodoItem: (Int, String) -> Unit = { _, _ -> },
            var removeTodoItem: (Int) -> Unit = { }
    ) : RProps()
}