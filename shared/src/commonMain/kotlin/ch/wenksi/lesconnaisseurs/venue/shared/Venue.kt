package ch.wenksi.lesconnaisseurs.venue.shared

import ch.wenksi.lesconnaisseurs.venue.map.Poi
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Instant

data class Venue(
    val name: String = "",
    val poi: Poi = Poi(
        id = Random.nextInt().toString(),
        name = "",
        lat = 0.0,
        lng = 0.0,
    ),
    val date: Instant = Clock.System.now(),
    val criteria: List<Criteria> = getAllCriteria(),
    val overallScore: Int = 0,
    val notes: String = "",
)

data class VenueUiState(
    val name: String = "",
    val poi: Poi = Poi(
        id = Random.nextInt().toString(),
        name = "",
        lat = 0.0,
        lng = 0.0,
    ),
    val readableDate: String = "",
    val criteria: List<Criteria> = getAllCriteria(),
    val overallScore: Int = 0,
    val notes: String = "",
)

fun Instant.toReadableString(
    timeZone: TimeZone = TimeZone.of("Europe/Zurich"),
): String {
    val date = toLocalDateTime(timeZone).date

    val months = listOf(
        "Januar", "Februar", "März", "April", "Mai", "Juni",
        "Juli", "August", "September", "Oktober", "November", "Dezember"
    )

    return "${date.day}. ${months[date.month.number - 1]} ${date.year}"
}

fun getAllCriteria(
    location: Int = 0,
    ambience: Int = 0,
    service: Int = 0,
    cuisine: Int = 0,
    price: Int = 0,
) = listOf(
    Criteria(location, "Standort", CriteriaType.LOCATION),
    Criteria(ambience, "Ambiente", CriteriaType.AMBIENCE),
    Criteria(service, "Service", CriteriaType.SERVICE),
    Criteria(cuisine, "Kulinarik", CriteriaType.CUISINE),
    Criteria(price, "Preis", CriteriaType.PRICE),
)

enum class CriteriaType {
    LOCATION, AMBIENCE, SERVICE, CUISINE, PRICE;
}

data class Criteria(
    val score: Int = 0,
    val name: String = "",
    val type: CriteriaType = CriteriaType.LOCATION,
)
