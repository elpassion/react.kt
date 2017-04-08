package org.jetbrains.firebase

private external val db: dynamic

fun writeTodoListState(id: String, state: Any?) {
    db.child("todo-list").child(id).set(state)
}

fun writeTicTacToeState(id: String, state: Any?) {
    db.child("tic-tac-toe").child(id).set(state)
}

fun subscribeToTodoListState(id: String, callback: (FirebaseUpdate) -> Unit) {
    db.child("todo-list").child(id).on("value", callback)
}

fun subscribeToTicTacToeState(id: String, callback: (FirebaseUpdate) -> Unit) {
    db.child("tic-tac-toe").child(id).on("value", callback)
}

external class FirebaseUpdate {
    fun `val`(): Array<String>?
}