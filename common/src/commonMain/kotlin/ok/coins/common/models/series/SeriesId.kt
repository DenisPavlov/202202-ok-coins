package ok.coins.common.models.series

import ok.coins.common.utils.NONE
import kotlin.jvm.JvmInline

@JvmInline
value class SeriesId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = SeriesId(String.NONE)
    }
}
