@file:JsModule("firebase/auth")
@file:JsNonModule

package lesconnaisseurs.firebase

import kotlin.js.Promise

external fun getAuth(app: FirebaseApp): FirebaseAuth
external fun signInWithEmailAndPassword(
    auth: FirebaseAuth,
    email: String,
    password: String
): Promise<UserCredential>

external fun signOut(auth: FirebaseAuth): Promise<Unit>

external fun onAuthStateChanged(
    auth: FirebaseAuth,
    nextOrObserver: (FirebaseUser?) -> Unit
): () -> Unit

external interface FirebaseAuth
external interface UserCredential {
    val user: FirebaseUser
}

external interface FirebaseUser {
    val uid: String
    val email: String?
}
