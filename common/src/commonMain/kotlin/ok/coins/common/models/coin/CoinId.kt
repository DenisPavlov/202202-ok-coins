package ok.coins.common.models.coin

import ok.coins.common.utils.NONE
import kotlin.jvm.JvmInline

@JvmInline
value class CoinId(private val id: String) {
    fun asString() = id
    companion object {
        val NONE = CoinId(String.NONE)
    }
}
