package ok.coins.web.common

import kotlinx.serialization.Serializable

@Serializable
data class Series(
    val id: String,
    val name: String,
)
