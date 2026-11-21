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
import androidx.compose.runtime.MutableState
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
import ch.wenksi.lesconnaisseurs.venue.nav.Leaderboard
import ch.wenksi.lesconnaisseurs.venue.nav.Rating
import ch.wenksi.lesconnaisseurs.venue.nav.Route
import ch.wenksi.lesconnaisseurs.venue.nav.routeConfig
import ch.wenksi.lesconnaisseurs.venue.ranking.LeaderboardScreen
import ch.wenksi.lesconnaisseurs.venue.ranking.LeaderboardViewModel
import ch.wenksi.lesconnaisseurs.venue.rate.RatingScreen
import ch.wenksi.lesconnaisseurs.venue.rate.RatingViewModel

@Composable
fun App(
    loginViewModel: LoginViewModel,
    ratingViewModel: RatingViewModel,
    leaderboardViewModel: LeaderboardViewModel,
) {
    LesConnaisseursAppTheme {
        val backStack = rememberNavBackStack(routeConfig, Login)
        val currentRoute = rememberSaveable { mutableStateOf<Route>(Leaderboard) }

        val navigateToTab: (Route) -> Unit = {
            currentRoute.value = it
            backStack.add(it)
        }

        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            bottomBar = {
                if (!backStack.contains(Login)) {
                    BottomNavigationBar(
                        currentRoute.value,
                        onNavigate = navigateToTab,
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
                                        backStack.add(Leaderboard)
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
                                onSaveRating = {
                                    ratingViewModel.onSaveRating {
                                        navigateToTab(Leaderboard)
                                    }
                                },
                            )
                        }
                        entry<Leaderboard> {
                            val venues by leaderboardViewModel.venues.collectAsStateWithLifecycle()
                            LeaderboardScreen(venues)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentRoute: Route,
    onNavigate: (Route) -> Unit,
) {
    NavigationBar {
        BottomNavItems.forEach { navItem ->
            println("$currentRoute == ${navItem.route}")
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
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
