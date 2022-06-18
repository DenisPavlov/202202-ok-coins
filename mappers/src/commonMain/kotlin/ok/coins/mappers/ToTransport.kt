package ok.coins.mappers

import ok.coins.common.CoinsCatalogContext
import ok.coins.common.models.CoinsCatalogError
import ok.coins.common.models.CoinsCatalogState
import ok.coins.common.models.coin.Coin
import ok.coins.common.utils.NONE
import ok.coins.common.utils.takeIfNotNone
import ru.otus.otuskotlin.coins.api.v1.CoinCreateResponse
import ru.otus.otuskotlin.coins.api.v1.Error
import ru.otus.otuskotlin.coins.api.v1.ResponseResult
import kotlinx.datetime.LocalDate
import ru.otus.otuskotlin.coins.api.v1.Coin as ApiCoin

fun CoinsCatalogContext.toTransportCreate() = CoinCreateResponse(
    responseType = "create",
    requestId = this.requestId.asString().takeIfNotNone(),
    result = state.toTransport(),
    errors = errors.toTransport(),
    coin = coinResponse.toTransport()
)

private fun Coin.toTransport() = ApiCoin(
    name = name.takeIfNotNone(),
    releaseDate = releaseDate.takeIf { it != LocalDate.NONE }?.toString(),
    cNum = cNum.takeIfNotNone(),

)

private fun List<CoinsCatalogError>.toTransport(): List<Error>? =
    this.map { it.toTransport() }.takeIf { it.isNotEmpty() }

private fun CoinsCatalogError.toTransport() = Error(
    code = code.takeIfNotNone(),
    message = message.takeIfNotNone(),
)

private fun CoinsCatalogState.toTransport(): ResponseResult? = when (this) {
    CoinsCatalogState.RUNNING, CoinsCatalogState.FINISHING -> ResponseResult.SUCCESS
    CoinsCatalogState.FAILING -> ResponseResult.ERROR
    else -> null
}