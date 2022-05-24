package ok.coins.web.front

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.serialization.json.Json
import ok.coins.web.common.AddCoin
import ok.coins.web.common.Series
import ru.otus.otuskotlin.coins.api.v1.BaseCoin

val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved
// val endpoint = "http://0.0.0.0:9090" // for local testing

val scope = MainScope()

private val client = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
}

suspend fun getCoinsBySeriesId(sId: String): List<BaseCoin> {
    return client.get("$endpoint/$sId/coins").body()
}

suspend fun getAllSeries(): List<Series> {
    return client.get("$endpoint/series").body()
}

suspend fun addCoin(coin: AddCoin) {
    client.post("$endpoint/coins") {
        contentType(ContentType.Application.Json)
        setBody(coin)
    }
}
