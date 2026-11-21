package ch.wenksi.lesconnaisseurs.venue.rate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.wenksi.lesconnaisseurs.LesConnaisseursAppTheme
import ch.wenksi.lesconnaisseurs.venue.shared.Criteria
import ch.wenksi.lesconnaisseurs.venue.shared.CriteriaType
import ch.wenksi.lesconnaisseurs.venue.shared.FakeVenueUiState
import ch.wenksi.lesconnaisseurs.venue.shared.Header
import ch.wenksi.lesconnaisseurs.venue.shared.PanelCard

@Composable
fun RatingScreen(
    uiState: RatingUiState = RatingUiState(),
    onScoreChange: (Int, CriteriaType) -> Unit = { _, _ -> },
    onSaveRating: () -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onNotesChange: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Header()

        PanelCard {
            OutlinedTextField(
                value = uiState.venue.name,
                onValueChange = onNameChange,
                label = { Text("TITEL") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.hasValidationError,
            )
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Eyebrow("STANDORT")
                    Text("Luzern, Schweiz", style = MaterialTheme.typography.bodySmall)
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                ) {
                    Eyebrow("DATUM")
                    Text(
                        text = uiState.venue.readableDate,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        PanelCard {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Eyebrow("BEWERTUNG TOTAL")
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            uiState.venue.overallScore.toString(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(" / 100", style = MaterialTheme.typography.bodySmall)
                    }
                }
                Text(
                    "✦ GOURMET",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(Modifier.height(18.dp))
        Eyebrow("BEWERTUNG")
        Spacer(Modifier.height(8.dp))

        uiState.venue.criteria.forEach { criteria ->
            CriteriaRow(criteria) {
                onScoreChange(it, criteria.type)
            }
            Spacer(Modifier.height(7.dp))
        }

        Spacer(Modifier.height(10.dp))
        PanelCard {
            OutlinedTextField(
                value = uiState.venue.notes,
                onValueChange = onNotesChange,
                label = { Text("BEMERKUNG") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                minLines = 3,
                placeholder = {
                    Text("Es war schön!")
                }
            )
        }

        Spacer(Modifier.height(14.dp))
        Button(
            onClick = onSaveRating,
            modifier = Modifier.fillMaxWidth().height(44.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Icon(Icons.Default.Check, contentDescription = null)
            Spacer(Modifier.width(7.dp))
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun CriteriaRow(
    criteria: Criteria,
    onScoreChange: (Int) -> Unit,
) {
    PanelCard {
        Column {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(criteria.name, style = MaterialTheme.typography.bodyMedium)
                Text(
                    "${criteria.score} / 20",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.height(12.dp))

            Column {
                Slider(
                    value = criteria.score.toFloat(),
                    onValueChange = { onScoreChange(it.toInt()) },
                    valueRange = 1f..20f,
                    steps = 18
                )
            }
        }
    }
}

@Composable
private fun Eyebrow(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        letterSpacing = 1.2.sp,
        modifier = Modifier.padding(bottom = 7.dp)
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    LesConnaisseursAppTheme {
        RatingScreen(
            uiState = RatingUiState(
                venue = FakeVenueUiState,
                hasValidationError = false,
            ),
        )
    }
}
