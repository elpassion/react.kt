package org.jetbrains.firebase

data class FirebaseConfig(
        val apiKey: String,
        val authDomain: String,
        val databaseURL: String,
        val projectId: String,
        val storageBucket: String,
        val messagingSenderId: String
)

private val config = FirebaseConfig(
    apiKey = "AIzaSyAQ7ahyThm0izbbOWD83Hy1KbVsFC7X1pE",
    authDomain = "react-kt.firebaseapp.com",
    databaseURL = "https://react-kt.firebaseio.com",
    projectId = "react-kt",
    storageBucket = "react-kt.appspot.com",
    messagingSenderId = "182183482939"
)

private external val firebase: dynamic

private val db : dynamic by lazy {
    firebase.initializeApp(config)
    firebase.database().ref()
}


fun writeTodoListState(id: String, state: Any?) {
    db.child("todo-list").child(id).set(state)
}

fun writeTicTacToeState(id: String, state: Any?) {
    db.child("tic-tac-toe").child(id).set(state)
}

fun subscribeToTodoListState(id: String, callback: (Array<String>?) -> Unit) {
    val cb : (FireData) -> Unit = { callback(it.`val`()) }
    db.child("todo-list").child(id).on("value", cb)
}

fun subscribeToTicTacToeState(id: String, callback: (Array<String>?) -> Unit) {
    val cb : (FireData) -> Unit = { callback(it.`val`()) }
    db.child("tic-tac-toe").child(id).on("value", cb)
}

private external class FireData {
    fun `val`(): Array<String>?
}