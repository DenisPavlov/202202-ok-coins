package ok.coins.common.models

import ok.coins.common.utils.NONE
import kotlin.jvm.JvmInline

@JvmInline
value class CoinsCatalogRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = CoinsCatalogRequestId(String.NONE)
    }
}
