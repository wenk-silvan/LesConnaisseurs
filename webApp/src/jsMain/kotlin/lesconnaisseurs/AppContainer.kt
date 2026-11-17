package lesconnaisseurs

import ch.wenksi.lesconnaisseurs.login.LoginViewModel
import ch.wenksi.lesconnaisseurs.venue.rate.RatingViewModel
import lesconnaisseurs.auth.FirebaseAuthRepository

class AppContainer {
    val authRepository = FirebaseAuthRepository()
    val loginViewModel = LoginViewModel(authRepository)
    val ratingViewModel = RatingViewModel()
}
