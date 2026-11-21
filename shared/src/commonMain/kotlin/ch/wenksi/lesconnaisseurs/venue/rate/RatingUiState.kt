package ch.wenksi.lesconnaisseurs.venue.rate

import ch.wenksi.lesconnaisseurs.venue.shared.VenueUiState

data class RatingUiState(
    val venue: VenueUiState = VenueUiState(),
    val hasValidationError: Boolean = false,
)
