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
var dbref = firebase.database().ref().child('todo-state');

function firebase_element_callback(element) {
    console.log(element.val());
}

function firebase_callback(snapshot) {
    snapshot.forEach(firebase_element_callback);
}

dbref.on("value", firebase_callback);
