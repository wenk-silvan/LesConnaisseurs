package ch.wenksi.lesconnaisseurs.venue.map

import kotlinx.serialization.Serializable

@Serializable
data class Poi(
    val id: String,
    val name: String,
    val lat: Double,
    val lng: Double
)
