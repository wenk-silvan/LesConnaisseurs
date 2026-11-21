package ch.wenksi.lesconnaisseurs.venue.ranking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.wenksi.lesconnaisseurs.LesConnaisseursAppTheme
import ch.wenksi.lesconnaisseurs.venue.shared.FakeVenueUiState
import ch.wenksi.lesconnaisseurs.venue.shared.Header
import ch.wenksi.lesconnaisseurs.venue.shared.PanelCard
import ch.wenksi.lesconnaisseurs.venue.shared.VenueUiState

@Composable
fun LeaderboardScreen(venues: List<VenueUiState>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Header()

        LazyColumn {
            itemsIndexed(venues) { _, venue ->
                RankedVenue(
                    venue,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}


@Composable
private fun RankedVenue(venue: VenueUiState, modifier: Modifier = Modifier) {
    Box(modifier) {
        PanelCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    venue.rank.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(venue.name, style = MaterialTheme.typography.headlineMedium)
                    Text(venue.readableDate, style = MaterialTheme.typography.labelSmall)
                }
                Spacer(Modifier.weight(1f))
                Text(
                    "${venue.overallScore}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    " / 100",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    LesConnaisseursAppTheme {
        LeaderboardScreen(
            venues = listOf(
                FakeVenueUiState.copy(rank = 1, overallScore = 88),
                FakeVenueUiState.copy(rank = 2, overallScore = 62),
                FakeVenueUiState.copy(rank = 3, overallScore = 21),
                FakeVenueUiState.copy(rank = 4, overallScore = 5),
            ),
        )
    }
}
