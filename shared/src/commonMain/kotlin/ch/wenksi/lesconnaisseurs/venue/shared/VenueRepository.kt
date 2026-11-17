package ch.wenksi.lesconnaisseurs.venue.shared

interface VenueRepository {

    suspend fun getVenues(): List<Venue>

    suspend fun saveVenue(venue: Venue)
}
