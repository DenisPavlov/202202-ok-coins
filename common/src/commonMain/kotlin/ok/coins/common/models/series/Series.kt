package ok.coins.common.models.series

import ok.coins.common.utils.NONE

data class Series(
    var id: SeriesId = SeriesId.NONE,
    var name: String = String.NONE,
)
