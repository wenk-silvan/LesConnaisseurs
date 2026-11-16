package ch.wenksi.lesconnaisseurs.login

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun logout()
    suspend fun currentUser(): User?
}
