package lesconnaisseurs.firebase

import SecretFirebaseOptions

external interface FirebaseOptions {
    var apiKey: String
    var authDomain: String
    var projectId: String
    var appId: String
}

fun firebaseOptions(): dynamic = js(SecretFirebaseOptions)
