package ch.wenksi.lesconnaisseurs.venue.shared

import ch.wenksi.lesconnaisseurs.venue.map.Poi
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Instant

@Serializable
data class Venue(
    val id: String = "",
    val name: String = "",
    val poi: Poi = Poi(
        id = Random.nextInt().toString(),
        name = "",
        lat = 0.0,
        lng = 0.0,
    ),
    val date: Double = Clock.System.now().toEpochMilliseconds().toDouble(),
    val criteria: List<Criteria> = getAllCriteria(),
    val overallScore: Int = 0,
    val notes: String = "",
) {
    fun toUiState(rank: Int) = VenueUiState(
        name = name,
        poi = poi,
        readableDate = Instant.fromEpochMilliseconds(date.toLong()).toReadableString(),
        criteria = criteria,
        overallScore = overallScore,
        notes = notes,
        rank = rank,
    )
}

val FakeVenueUiState = VenueUiState(
    name = "Le Gabriel",
    criteria = getAllCriteria(1, 2, 3, 4, 5),
    overallScore = 40,
    readableDate = "12. Oktober 2026",
    notes = "Banon's beautiful sun-golden goat cheese paired " +
            "subtly. Remarkable taste. The atmosphere felt " +
            "absolute auditory discretion.",
    rank = 1,
)

data class VenueUiState(
    val name: String = "",
    val poi: Poi = Poi(
        id = Random.nextInt().toString(),
        name = "",
        lat = 0.0,
        lng = 0.0,
    ),
    val readableDate: String = Clock.System.now().toReadableString(),
    val criteria: List<Criteria> = getAllCriteria(),
    val overallScore: Int = 0,
    val notes: String = "",
    val rank: Int = 0,
) {
    fun toVenue() = Venue(
        id = Random.nextInt().toString(),
        name = name,
        poi = poi,
        date = Clock.System.now().toEpochMilliseconds().toDouble(),
        criteria = criteria,
        overallScore = overallScore,
        notes = notes,
    )
}

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

@Serializable
data class Criteria(
    val score: Int = 0,
    val name: String = "",
    val type: CriteriaType = CriteriaType.LOCATION,
)
