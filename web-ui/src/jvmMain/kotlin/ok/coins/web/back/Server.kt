package ok.coins.web.back

import com.typesafe.config.ConfigFactory
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.gzip
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import ok.coins.web.common.AddCoin
import ok.coins.web.common.Coin
import ok.coins.web.common.Series

val config = HoconApplicationConfig(ConfigFactory.load())
val port = config.property("ktor.deployment.port").getString().toInt()

fun main() {
    embeddedServer(Netty, port) {
        install(ContentNegotiation) {
            json()
        }

        install(Compression) {
            gzip()
        }

        // todo - временное решение для тестирования
        install(CORS) {
            anyHost()
            allowHeader(HttpHeaders.ContentType)
        }

        routing {
            get("/") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html,
                )
            }
            static("/") {
                resources("")
            }

            route("/{seriesId}/coins") {
                get {
                    val seriesId = call.parameters["seriesId"] ?: error("Invalid request")
                    call.respond(coins.filter { it.seriesId == seriesId })
                }
            }

            route("/series") {
                get {
                    call.respond(series)
                }
            }

            route("/coins") {
                post {
                    val addCoin: AddCoin = call.receive()
                    coins.add(
                        Coin(
                            img = "https://www.cbr.ru/legacy/PhotoStore/img/${addCoin.cNum ?: ""}r.jpg",
                            name = addCoin.name ?: "",
                            nominal = addCoin.nominal ?: "",
                            releaseYear = addCoin.releaseDate?.substringBefore("-") ?: "",
                            seriesId = "id-3"
                        )
                    )
                }
            }
        }
    }.start(wait = true)
}

val series = listOf(
    Series("id-1", "Оружие Великой Победы (конструкторы оружия)"),
    Series("id-2", "Вооруженные силы Российской Федерации"),
    Series("id-3", "Древние города России"),
)

val coins = mutableListOf(
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5714-0072r.jpg",
        name = "г. Нижний Новгород, Нижегородская область",
        nominal = "10 рублей",
        releaseYear = "2021",
        seriesId = "id-3"
    ),
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5714-0069r.jpg",
        name = "г. Козельск, Калужская область",
        nominal = "10 рублей",
        releaseYear = "2020",
        seriesId = "id-3"
    ),
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5714-0065r.jpg",
        name = "г. Клин, Московская область",
        nominal = "10 рублей",
        releaseYear = "2019",
        seriesId = "id-3"
    ),
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5714-0063r.jpg",
        name = "г. Вязьма, Смоленская область",
        nominal = "10 рублей",
        releaseYear = "2019",
        seriesId = "id-3"
    ),
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5714-0060r.jpg",
        name = "г. Гороховец, Владимирская область (1168 г.)",
        nominal = "10 рублей",
        releaseYear = "2018",
        seriesId = "id-3"
    ),
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5714-0056r.jpg",
        name = "г. Олонец, Республика Карелия (1137 г.)",
        nominal = "10 рублей",
        releaseYear = "2017",
        seriesId = "id-3"
    ),
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5015-0048r.jpg",
        name = "Конструктор оружия С.А. Лавочкин",
        nominal = "10 рублей",
        releaseYear = "2020",
        seriesId = "id-1"
    ),
    Coin(
        img = "https://www.cbr.ru/legacy/PhotoStore/img/5015-0047r.jpg",
        name = "Конструктор оружия С.В. Ильюшин",
        nominal = "10 рублей",
        releaseYear = "2020",
        seriesId = "id-1"
    ),
)
