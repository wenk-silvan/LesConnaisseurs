package ch.wenksi.lesconnaisseurs.venue.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CompassCalibration
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: Route,
)

val BottomNavItems = listOf(
    BottomNavItem(
        label = "Leaderboard",
        icon = Icons.Outlined.Leaderboard,
        route = Leaderboard,
    ),
    BottomNavItem(
        label = "Rate",
        icon = Icons.Outlined.RateReview,
        route = Rating,
    ),
    BottomNavItem(
        label = "Map",
        icon = Icons.Outlined.Map,
        route = Map,
    ),
)


