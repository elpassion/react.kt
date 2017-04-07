// Initialize Firebase
var config = {
    apiKey: "AIzaSyAQ7ahyThm0izbbOWD83Hy1KbVsFC7X1pE",
    authDomain: "react-kt.firebaseapp.com",
    databaseURL: "https://react-kt.firebaseio.com",
    projectId: "react-kt",
    storageBucket: "react-kt.appspot.com",
    messagingSenderId: "182183482939"
};
firebase.initializeApp(config);
var db = firebase.database().ref();

function firebase_element_callback(element) {
    console.log(element.val());
}

function firebase_callback(snapshot) {
    snapshot.forEach(firebase_element_callback);
}

function generateUUID() { // Public Domain/MIT
    var d = new Date().getTime();
    if (typeof performance !== 'undefined' && typeof performance.now === 'function'){
        d += performance.now(); //use high-precision timer if available
    }
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}

function writeTodoListState(id, state) {
    db.child("todo-list").child(id).set(state);
}

function writeTicTacToeState(id, state) {
    db.child("tic-tac-toe").child(id).set(state);
}

db.on("value", firebase_callback);