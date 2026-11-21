package ch.wenksi.lesconnaisseurs.venue.rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.wenksi.lesconnaisseurs.venue.shared.CriteriaType
import ch.wenksi.lesconnaisseurs.venue.shared.VenueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RatingViewModel(
    private val venueRepository: VenueRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RatingUiState())

    val uiState: StateFlow<RatingUiState> = _uiState.asStateFlow()

    fun onScoreChange(value: Int, type: CriteriaType) {
        _uiState.update { state ->
            val newCriteria = state.venue.criteria.map { criteria ->
                if (criteria.type == type) criteria.copy(score = value)
                else criteria
            }
            state.copy(
                venue = state.venue.copy(
                    criteria = newCriteria,
                    overallScore = newCriteria.sumOf { it.score },
                )
            )
        }
    }

    fun onNameChange(value: String) {
        _uiState.update {
            val venue = it.venue.copy(name = value)
            if (_uiState.value.hasValidationError || value.isNotBlank()) {
                it.copy(
                    hasValidationError = false,
                    venue = venue,
                )
            } else {
                it.copy(
                    venue = venue,
                )
            }
        }

    }

    fun onNotesChange(value: String) {
        _uiState.update { it.copy(venue = it.venue.copy(notes = value)) }
    }

    fun onSaveRating(onSuccess: () -> Unit) {
        with(_uiState.value.venue) {
            if (name.isBlank()) {
                _uiState.update {
                    it.copy(
                        hasValidationError = true,
                    )
                }
                return
            }
        }

        viewModelScope.launch {
            venueRepository.saveVenue(_uiState.value.venue.toVenue())
            onSuccess()
        }
    }
}
