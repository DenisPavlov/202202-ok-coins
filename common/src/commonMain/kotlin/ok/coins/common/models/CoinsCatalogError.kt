package ok.coins.common.models

import ok.coins.common.utils.NONE

data class CoinsCatalogError(
    val code: String = String.NONE,
    val group: String = String.NONE,
    val field: String = String.NONE,
    val message: String = String.NONE,
    val exception: Throwable? = null,
)
