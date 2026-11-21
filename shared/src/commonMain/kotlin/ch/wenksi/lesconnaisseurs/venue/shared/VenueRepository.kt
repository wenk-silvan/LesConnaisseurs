package ch.wenksi.lesconnaisseurs.venue.shared

import kotlinx.coroutines.flow.Flow

interface VenueRepository {

    fun getVenues(): Flow<List<Venue>>

    suspend fun saveVenue(venue: Venue)
}
