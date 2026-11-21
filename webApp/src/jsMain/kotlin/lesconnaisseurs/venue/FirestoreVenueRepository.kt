package lesconnaisseurs.venue

import ch.wenksi.lesconnaisseurs.venue.shared.Venue
import ch.wenksi.lesconnaisseurs.venue.shared.VenueRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirestoreVenueRepository : VenueRepository {

    override fun getVenues(): Flow<List<Venue>> {
        return Firebase.firestore
            .collection("venues")
            .snapshots
            .map { snapshot ->
                snapshot.documents.map { document ->
                    document.data<Venue>().copy(id = document.id)
                }
            }
    }

    override suspend fun saveVenue(venue: Venue) {
        Firebase.firestore
            .collection("venues")
            .add(venue)
    }
}
