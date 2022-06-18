package ok.coins.mappers

import kotlinx.datetime.LocalDate
import ok.coins.common.CoinsCatalogContext
import ok.coins.common.models.CoinsCatalogCommand
import ok.coins.common.models.CoinsCatalogRequestId
import ok.coins.common.models.CoinsCatalogWorkMode
import ok.coins.common.models.coin.Coin
import ok.coins.common.models.coin.Diameter
import ok.coins.common.models.coin.Nominal
import ok.coins.common.models.coin.Thickness
import ok.coins.common.models.coin.Weight
import ok.coins.common.models.series.SeriesId
import ok.coins.common.utils.NONE
import ok.coins.mappers.exceptions.UnknownRequestClass
import ru.otus.otuskotlin.coins.api.v1.Coin as ApiCoin
import ru.otus.otuskotlin.coins.api.v1.CoinCreateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteRequest
import ru.otus.otuskotlin.coins.api.v1.CoinReadRequest
import ru.otus.otuskotlin.coins.api.v1.CoinRequestDebugMode
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateRequest
import ru.otus.otuskotlin.coins.api.v1.IRequest


fun CoinsCatalogContext.fromTransport(request: IRequest) = when(request) {
    is CoinCreateRequest -> fromTransport(request)
    is CoinReadRequest -> fromTransport(request)
    is CoinUpdateRequest -> fromTransport(request)
    is CoinDeleteRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request::class)
}

private fun CoinsCatalogContext.fromTransport(request: CoinCreateRequest) {
    command = CoinsCatalogCommand.CREATE_COIN
    workMode = request.mode.toWorkMode()
    requestId = request.toRequestId()
    coinRequest = request.coin?.toInternal() ?: Coin()
}

private fun CoinsCatalogContext.fromTransport(request: CoinReadRequest) {
    command = CoinsCatalogCommand.READ_COIN
    workMode = request.mode.toWorkMode()
    requestId = request.toRequestId()
    coinRequest = request.cNum?.let { Coin(cNum = it) } ?: Coin()
}

private fun CoinsCatalogContext.fromTransport(request: CoinUpdateRequest) {
    command = CoinsCatalogCommand.UPDATE_COIN
    workMode = request.mode.toWorkMode()
    requestId = request.toRequestId()
    coinRequest = request.coin?.toInternal() ?: Coin()
}

private fun CoinsCatalogContext.fromTransport(request: CoinDeleteRequest) {
    command = CoinsCatalogCommand.DELETE_COIN
    workMode = request.mode.toWorkMode()
    requestId = request.toRequestId()
    coinRequest = request.cNum?.let { Coin(cNum = it) } ?: Coin()
}

private fun CoinRequestDebugMode?.toWorkMode(): CoinsCatalogWorkMode = when(this) {
    CoinRequestDebugMode.TEST -> CoinsCatalogWorkMode.TEST
    null, CoinRequestDebugMode.PROD -> CoinsCatalogWorkMode.PROD
}

private fun IRequest?.toRequestId() = this?.requestId?.let { CoinsCatalogRequestId(it) } ?: CoinsCatalogRequestId.NONE

private fun String.toNominal(): Nominal = this.split(" ").let {(amount, currency) ->
    when {
        amount == "10" && currency == "рублей" -> Nominal.TEN_RUB
        else -> Nominal.NONE
    }
}

private fun String.toWeight() = this.split(" ").let { (amount, unit) ->
    Weight(amount.toDouble(), unit)
}

private fun String.toDiameter() = this.split(" ").let { (amount, unit) ->
    Diameter(amount.toDouble(), unit)
}

private fun String.toThickness() = this.split(" ").let {(amount, unit) ->
    Thickness(amount.toDouble(), unit)
}

private fun ApiCoin?.toInternal() = Coin(
    name = this?.name ?: String.NONE,
    seriesId = this?.seriesId?.let { SeriesId(it) } ?: SeriesId.NONE,
    releaseDate = this?.releaseDate?.let { LocalDate.parse(it) } ?: LocalDate.NONE,
    cNum = this?.cNum ?: String.NONE,
    nominal = this?.nominal?.toNominal() ?: Nominal.NONE,
    material = this?.material ?: String.NONE,
    weight = this?.weight?.toWeight() ?: Weight.NONE,
    diameter = this?.diameter?.toDiameter() ?: Diameter.NONE,
    thickness = this?.thickness?.toThickness() ?: Thickness.NONE,
    allCount = this?.allCount ?: Int.NONE,
    averse = this?.averse ?: String.NONE,
    reverse = this?.reverse ?: String.NONE,
    authors = this?.authors ?: String.NONE,
    info = this?.info ?: String.NONE,
    imgUri = this?.img ?: String.NONE,
)
