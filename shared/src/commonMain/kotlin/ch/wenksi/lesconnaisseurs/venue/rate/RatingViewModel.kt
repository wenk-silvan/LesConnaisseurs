package ch.wenksi.lesconnaisseurs.venue.rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.wenksi.lesconnaisseurs.venue.shared.CriteriaType
import ch.wenksi.lesconnaisseurs.venue.shared.Venue
import ch.wenksi.lesconnaisseurs.venue.shared.VenueRepository
import ch.wenksi.lesconnaisseurs.venue.shared.VenueUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Clock

class RatingViewModel(private val venueRepository: VenueRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(VenueUiState())

    val uiState: StateFlow<VenueUiState> = _uiState.asStateFlow()

    fun onScoreChange(value: Int, type: CriteriaType) {
        _uiState.update { state ->
            val newCriteria = state.criteria.map { criteria ->
                if (criteria.type == type) criteria.copy(score = value)
                else criteria
            }
            state.copy(
                criteria = newCriteria,
                overallScore = newCriteria.sumOf { it.score },
            )
        }
    }

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value) }

    }

    fun onNotesChange(value: String) {
        _uiState.update { it.copy(notes = value) }
    }

    fun onSaveRating() {
        val venue = with(_uiState.value) {
            Venue(
                id = Random.nextInt().toString(),
                name = name,
                poi = poi,
                date = Clock.System.now(),
                criteria = criteria,
                overallScore = overallScore,
                notes = notes,
            )
        }
        viewModelScope.launch {
            venueRepository.saveVenue(venue)
        }
    }
}
