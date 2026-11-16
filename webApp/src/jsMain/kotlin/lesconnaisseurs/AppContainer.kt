package lesconnaisseurs

import ch.wenksi.lesconnaisseurs.login.LoginViewModel
import lesconnaisseurs.auth.FirebaseAuthRepository

class AppContainer {
    val authRepository = FirebaseAuthRepository()
    val loginViewModel = LoginViewModel(authRepository)
}
