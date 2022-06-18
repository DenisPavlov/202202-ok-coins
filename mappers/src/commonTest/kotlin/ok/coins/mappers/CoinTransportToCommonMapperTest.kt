package ok.coins.mappers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
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
import ru.otus.otuskotlin.coins.api.v1.Coin as ApiCoin
import ru.otus.otuskotlin.coins.api.v1.CoinCreateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteRequest
import ru.otus.otuskotlin.coins.api.v1.CoinReadRequest
import ru.otus.otuskotlin.coins.api.v1.CoinRequestDebugMode
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateRequest

class CoinTransportToCommonMapperTest: StringSpec({

    "transform create coin transport to common" {
        val request = CoinCreateRequest(
            requestType = "create",
            requestId = "123",
            mode = CoinRequestDebugMode.TEST,
            coin = ApiCoin(
                name = "г. Нижний Новгород, Нижегородская область",
                nominal = "10 рублей",
                series = "Города трудовой доблести",
                seriesId = "series-id",
                releaseDate = "2021-11-18",
                cNum = "5714-0077",
                material = "сталь с латунным гальваническим покрытием",
                weight = "5.63 г",
                diameter = "22.0 мм",
                thickness = "2.20 мм",
                allCount = 1000000,
                averse = "в центре — обозначение номинала монеты: «10 РУБЛЕЙ»...",
                reverse = "рельефное изображение фрагмента памятника Героям фронта...",
                authors = "Художник: А.А. Брынза.",
                info = "За значительный вклад жителей городов в достижение Победы...",
                img = "https://www.cbr.ru/dzi/?tilesources=5714-0077r"
            )
        )

        val context = CoinsCatalogContext()
        context.fromTransport(request)

        with(context) {
            command shouldBe CoinsCatalogCommand.CREATE_COIN
            workMode shouldBe CoinsCatalogWorkMode.TEST
            requestId shouldBe CoinsCatalogRequestId(request.requestId!!)
        }

        with(context.coinRequest) {
            name shouldBe request.coin!!.name
            seriesId shouldBe SeriesId(request.coin!!.series!!)
            releaseDate shouldBe LocalDate(2021, Month.NOVEMBER, 18)
            cNum shouldBe request.coin!!.cNum
            nominal shouldBe Nominal.TEN_RUB
            material shouldBe request.coin!!.material
            weight shouldBe Weight(amount = 5.63, unit = "г")
            diameter shouldBe Diameter(amount = 22.0, unit = "мм")
            thickness shouldBe Thickness(amount = 2.20, unit = "мм")
            allCount shouldBe 1000000
            averse shouldBe request.coin!!.averse
            reverse shouldBe request.coin!!.reverse
            authors shouldBe request.coin!!.authors
            info shouldBe request.coin!!.info
            imgUri shouldBe request.coin!!.img
        }
    }

    "transform read coin transport to common" {
        val request = CoinReadRequest(
            requestType = "read",
            requestId = "123",
            mode = CoinRequestDebugMode.TEST,
            cNum = "5714-0077"
        )

        val context = CoinsCatalogContext()
        context.fromTransport(request)

        with(context) {
            command shouldBe CoinsCatalogCommand.READ_COIN
            workMode shouldBe CoinsCatalogWorkMode.TEST
            requestId shouldBe CoinsCatalogRequestId(request.requestId!!)
            coinRequest.cNum shouldBe request.cNum
        }
    }

    "transform update coin transport to common" {
        val request = CoinUpdateRequest(
            requestType = "update",
            requestId = "123",
            mode = CoinRequestDebugMode.PROD,
        )

        val context = CoinsCatalogContext()
        context.fromTransport(request)

        with(context) {
            command shouldBe CoinsCatalogCommand.UPDATE_COIN
            workMode shouldBe CoinsCatalogWorkMode.PROD
            requestId shouldBe CoinsCatalogRequestId(request.requestId!!)
            coinRequest shouldBe Coin()
        }
    }

    "transform delete coin transport to common" {
        val request = CoinDeleteRequest(
            requestType = "delete",
            requestId = "123",
            mode = CoinRequestDebugMode.TEST,
            cNum = "5714-0077"
        )

        val context = CoinsCatalogContext()
        context.fromTransport(request)

        with(context) {
            command shouldBe CoinsCatalogCommand.DELETE_COIN
            workMode shouldBe CoinsCatalogWorkMode.TEST
            requestId shouldBe CoinsCatalogRequestId(request.requestId!!)
            coinRequest.cNum shouldBe request.cNum
        }
    }
})
