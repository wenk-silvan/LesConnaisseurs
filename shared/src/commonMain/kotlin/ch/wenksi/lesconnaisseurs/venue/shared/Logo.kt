package ch.wenksi.lesconnaisseurs.venue.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ch.wenksi.lesconnaisseurs.LesConnaisseursAppTheme

@Composable
fun Logo() {
    Text(
        text = "LES CONNAISSEURS",
        style = MaterialTheme.typography.displayMedium,
    )
}

@Preview
@Composable
private fun Preview() {
    LesConnaisseursAppTheme {
        Logo()
    }
}
