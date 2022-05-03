package ok.coins.web.common

import kotlinx.serialization.Serializable

@Serializable
data class AddCoin(
    val name: String? = null,
    val series: String? = null,
    val releaseDate: String? = null,
    val cNum: String? = null,
    val nominal: String? = null,
    val quality: String? = null,
    val alloy: String? = null,
    val weight: String? = null,
    val diameter: String? = null,
    val thickness: String? = null,
    val allCount: String? = null,
    val averse: String? = null,
    val reverse: String? = null,
    val authors: String? = null,
    val info: String? = null,
)
