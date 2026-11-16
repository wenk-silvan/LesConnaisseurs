@file:JsModule("firebase/app")
@file:JsNonModule

package lesconnaisseurs.firebase

external fun initializeApp(config: FirebaseConfig): FirebaseApp

external interface FirebaseConfig {
    val apiKey: String
    val authDomain: String
    val projectId: String
    val storageBucket: String
    val messagingSenderId: String
    val appId: String
}

external interface FirebaseApp
