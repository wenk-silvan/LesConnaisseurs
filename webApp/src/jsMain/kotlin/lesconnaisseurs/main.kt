package lesconnaisseurs

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import ch.wenksi.lesconnaisseurs.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val container = AppContainer()
        App(
            container.loginViewModel,
            container.ratingViewModel,
        )
    }
}
