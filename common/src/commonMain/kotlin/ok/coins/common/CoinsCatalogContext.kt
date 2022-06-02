package ok.coins.common

import kotlinx.datetime.Instant
import ok.coins.common.models.CoinsCatalogCommand
import ok.coins.common.models.CoinsCatalogError
import ok.coins.common.models.CoinsCatalogRequestId
import ok.coins.common.models.CoinsCatalogState
import ok.coins.common.models.CoinsCatalogWorkMode
import ok.coins.common.models.coin.Coin
import ok.coins.common.models.series.Series
import ok.coins.common.utils.NONE

data class CoinsCatalogContext(
    val command: CoinsCatalogCommand = CoinsCatalogCommand.NONE,
    var state: CoinsCatalogState = CoinsCatalogState.NONE,
    val errors: MutableList<CoinsCatalogError> = mutableListOf(),
    val workMode: CoinsCatalogWorkMode = CoinsCatalogWorkMode.PROD,
    val requestId: CoinsCatalogRequestId = CoinsCatalogRequestId.NONE,
    val timeStart: Instant = Instant.NONE,

    // series
    var seriesRequest: Series = Series(),
    var seriesResponse: Series = Series(),
    var seriesListResponse: MutableList<Series> = mutableListOf(),

    // coins
    var coinRequest: Coin = Coin(),
    var coinResponse: Coin = Coin(),
    var coinsResponse: MutableList<Coin> = mutableListOf(),
)
