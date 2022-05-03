package ok.coins.web.common

import kotlinx.serialization.Serializable

@Serializable
data class Coin(
    val img: String,
    val nominal: String,
    val name: String,
    val releaseYear: String,
    val seriesId: String,
)
