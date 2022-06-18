package ok.coins.common.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

val String.Companion.NONE: String
    get() = ""

fun String.takeIfNotNone() = takeIf { it != String.NONE }

val Instant.Companion.NONE
    get() = fromEpochMilliseconds(Long.MIN_VALUE)

val LocalDate.Companion.NONE
    get() = LocalDate(9999,1,1)

val Int.Companion.NONE
    get() = MIN_VALUE