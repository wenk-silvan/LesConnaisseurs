package ch.wenksi.lesconnaisseurs.venue.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.wenksi.lesconnaisseurs.LesConnaisseursAppTheme

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 12.dp,
                bottom = 24.dp,
            ),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "LES CONNAISSEURS",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }

}

@Preview
@Composable
private fun Preview() {
    LesConnaisseursAppTheme {
        Header()
    }
}
