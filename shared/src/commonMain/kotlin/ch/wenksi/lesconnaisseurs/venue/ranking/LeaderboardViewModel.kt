package ch.wenksi.lesconnaisseurs.venue.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.wenksi.lesconnaisseurs.venue.shared.Venue
import ch.wenksi.lesconnaisseurs.venue.shared.VenueRepository
import ch.wenksi.lesconnaisseurs.venue.shared.VenueUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LeaderboardViewModel(venueRepository: VenueRepository) : ViewModel() {

    val venues: StateFlow<List<VenueUiState>> = venueRepository
        .getVenues()
        .map { list ->
            list
                .sortedByDescending { it.overallScore }
                .mapIndexed { index, venue ->
                    venue.toUiState(
                        rank = index + 1,
                    )
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
