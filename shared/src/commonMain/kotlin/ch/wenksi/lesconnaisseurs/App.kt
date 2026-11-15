package ch.wenksi.lesconnaisseurs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import ch.wenksi.lesconnaisseurs.detail.DetailScreen
import ch.wenksi.lesconnaisseurs.login.LoginScreen
import ch.wenksi.lesconnaisseurs.map.MapScreen
import ch.wenksi.lesconnaisseurs.nav.Detail
import ch.wenksi.lesconnaisseurs.nav.Login
import ch.wenksi.lesconnaisseurs.nav.Map
import ch.wenksi.lesconnaisseurs.nav.routeConfig

@Composable
@Preview
fun App() {
    LesConnaisseursAppTheme {
        val backStack = rememberNavBackStack(routeConfig, Login)

        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<Login> {
                        LoginScreen(
                            onLogin = { _, _ ->
                                backStack.add(Map)
                            }
                        )
                    }
                    entry<Map> {
                        MapScreen()
                    }
                    entry<Detail> {
                        DetailScreen()
                    }
                }
            )
        }
    }
}
