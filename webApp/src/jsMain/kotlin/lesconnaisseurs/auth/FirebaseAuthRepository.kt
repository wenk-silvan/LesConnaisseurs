package lesconnaisseurs.auth

import lesconnaisseurs.firebase.getAuth
import lesconnaisseurs.firebase.initializeApp
import lesconnaisseurs.firebase.signInWithEmailAndPassword
import lesconnaisseurs.firebase.signOut
import ch.wenksi.lesconnaisseurs.login.AuthRepository
import ch.wenksi.lesconnaisseurs.login.User
import lesconnaisseurs.firebase.firebaseOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.js.Promise

class FirebaseAuthRepository : AuthRepository {
    private val app = initializeApp(firebaseOptions())
    private val auth = getAuth(app)

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val result = signInWithEmailAndPassword(auth, email, password).await()
            val firebaseUser = result.user

            if (firebaseUser == null) {
                Result.failure(IllegalStateException("No user returned"))
            } else {
                Result.success(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email
                    )
                )
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun logout() {
        signOut(auth).await()
    }

    override suspend fun currentUser(): User? {
        return null
    }
}

private suspend fun <T> Promise<T>.await(): T = suspendCancellableCoroutine { cont ->
        then(
            onFulfilled = { value -> cont.resume(value) },
            onRejected = { error -> cont.resumeWithException(error) }
        )
    }
