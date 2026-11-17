package lesconnaisseurs

import ch.wenksi.lesconnaisseurs.login.LoginViewModel
import ch.wenksi.lesconnaisseurs.venue.rate.RatingViewModel
import lesconnaisseurs.auth.FirebaseAuthRepository
import lesconnaisseurs.venue.FirestoreVenueRepository

class AppContainer {
    val authRepository = FirebaseAuthRepository()
    val venueRepository = FirestoreVenueRepository()
    val loginViewModel = LoginViewModel(authRepository)
    val ratingViewModel = RatingViewModel(venueRepository)
}
