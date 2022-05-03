package ok.coins.web.front.components

import kotlinx.coroutines.launch
import ok.coins.web.common.Series
import ok.coins.web.front.getAllSeries
import ok.coins.web.front.scope
import react.VFC
import react.useEffectOnce
import react.useState

val Collection = VFC {

    var series by useState(emptyList<Series>())

    useEffectOnce {
        scope.launch {
            series = getAllSeries()
        }
    }

    series.forEach {
        Series {
            id = it.id
            name = it.name
        }
    }
}
