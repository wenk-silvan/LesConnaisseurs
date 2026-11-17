package ch.wenksi.lesconnaisseurs.venue.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CompassCalibration
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: Route,
)

val BottomNavItems = listOf(
    BottomNavItem(
        label = "Map",
        icon = Icons.Outlined.CompassCalibration,
        route = Map,
    ),
    BottomNavItem(
        label = "Rate",
        icon = Icons.Outlined.Edit,
        route = Rating,
    ),
    BottomNavItem(
        label = "Ranking",
        icon = Icons.Outlined.Star,
        route = Ranking,
    ),
)


