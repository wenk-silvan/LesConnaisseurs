package lesconnaisseurs.venue

import SecretFirebaseOptions
import ch.wenksi.lesconnaisseurs.venue.shared.Venue
import ch.wenksi.lesconnaisseurs.venue.shared.VenueRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.initialize

class FirestoreVenueRepository : VenueRepository {
    override suspend fun getVenues(): List<Venue> {
        initializeFirebase()
        return emptyList()
    }

    override suspend fun saveVenue(venue: Venue) {
        Firebase.firestore
            .collection("venues")
            .add(venue)
    }

    private fun initializeFirebase() {
        Firebase.initialize(options = SecretFirebaseOptions)
    }
}
