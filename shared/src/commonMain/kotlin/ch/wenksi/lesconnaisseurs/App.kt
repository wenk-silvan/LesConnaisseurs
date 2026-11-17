package ch.wenksi.lesconnaisseurs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import ch.wenksi.lesconnaisseurs.login.LoginScreen
import ch.wenksi.lesconnaisseurs.login.LoginViewModel
import ch.wenksi.lesconnaisseurs.venue.map.MapScreen
import ch.wenksi.lesconnaisseurs.venue.nav.BottomNavItems
import ch.wenksi.lesconnaisseurs.venue.nav.Login
import ch.wenksi.lesconnaisseurs.venue.nav.Map
import ch.wenksi.lesconnaisseurs.venue.nav.Ranking
import ch.wenksi.lesconnaisseurs.venue.nav.Rating
import ch.wenksi.lesconnaisseurs.venue.nav.Route
import ch.wenksi.lesconnaisseurs.venue.nav.routeConfig
import ch.wenksi.lesconnaisseurs.venue.ranking.RankingScreen
import ch.wenksi.lesconnaisseurs.venue.rate.RatingScreen
import ch.wenksi.lesconnaisseurs.venue.rate.RatingViewModel
import ch.wenksi.lesconnaisseurs.venue.shared.VenueRepository

@Composable
fun App(
    loginViewModel: LoginViewModel,
    ratingViewModel: RatingViewModel,
) {
    LesConnaisseursAppTheme {
        val backStack = rememberNavBackStack(routeConfig, Login)

        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            bottomBar = {
                if (!backStack.contains(Login)) {
                    BottomNavigationBar(
                        onNavigate = {
                            backStack.add(it)
                        }
                    )
                }
            }
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<Login> {
                            val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
                            LoginScreen(
                                uiState = uiState,
                                onEmailChanged = loginViewModel::onEmailChange,
                                onPwChanged = loginViewModel::onPasswordChange,
                                onLogin = {
                                    loginViewModel.login {
                                        backStack.add(Map)
                                        backStack.remove(Login)
                                    }
                                }
                            )
                        }
                        entry<Map> {
                            MapScreen()
                        }
                        entry<Rating> {
                            val uiState by ratingViewModel.uiState.collectAsStateWithLifecycle()
                            RatingScreen(
                                uiState,
                                onScoreChange = ratingViewModel::onScoreChange,
                                onNameChange = ratingViewModel::onNameChange,
                                onNotesChange = ratingViewModel::onNotesChange,
                                onSaveRating = ratingViewModel::onSaveRating,
                            )
                        }
                        entry<Ranking> {
                            RankingScreen()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    onNavigate: (Route) -> Unit,
    initalRoute: Route = Map,
) {
    NavigationBar {
        val currentRoute = rememberSaveable { mutableStateOf(initalRoute) }
        BottomNavItems.forEach { navItem ->
            println("$currentRoute == ${navItem.route}")
            NavigationBarItem(
                selected = currentRoute.value == navItem.route,
                onClick = {
                    currentRoute.value = navItem.route
                    onNavigate(navItem.route)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(),
            )
        }
    }
}
