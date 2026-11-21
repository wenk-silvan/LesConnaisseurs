package ch.wenksi.lesconnaisseurs.venue.nav

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface Route : NavKey

@Serializable
data object Login : Route

@Serializable
data object Map : Route
@Serializable
data object Rating : Route

@Serializable
data object Leaderboard : Route

@Serializable
data class Detail(val id: String) : Route

val routeConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Login::class, Login.serializer())
            subclass(Rating::class, Rating.serializer())
            subclass(Leaderboard::class, Leaderboard.serializer())
            subclass(Detail::class, Detail.serializer())
            subclass(Map::class, Map.serializer())
        }
    }
}
