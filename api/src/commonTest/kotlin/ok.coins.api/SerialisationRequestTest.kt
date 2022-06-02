package ok.coins.api

import ru.otus.otuskotlin.coins.api.v1.Coin
import ru.otus.otuskotlin.coins.api.v1.CoinCreateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteRequest
import ru.otus.otuskotlin.coins.api.v1.CoinReadRequest
import ru.otus.otuskotlin.coins.api.v1.CoinRequestDebugMode
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateRequest
import kotlin.test.Test
import kotlin.test.assertContains

internal class SerialisationRequestTest {

    @Test
    fun createRequestSerializeTest() {
        val createRequest = CoinCreateRequest(
            requestType = "create",
            requestId = "123",
            mode = CoinRequestDebugMode.TEST,
            coin = Coin(
                name = "г. Нижний Новгород, Нижегородская область",
                nominal = "10 рублей"
            )
        )

        val result = apiRequestSerialize(createRequest)
        assertContains(result, """"name":"г. Нижний Новгород, Нижегородская область"""")
    }

    @Test
    fun readRequestSerializeTest() {
        val readRequest = CoinReadRequest(
            requestType = "create",
            requestId = "123",
            mode = CoinRequestDebugMode.TEST,
            cNum = "123"
        )

        val result = apiRequestSerialize(readRequest)
        assertContains(result, """"cNum":"123"""")
    }

    @Test
    fun updateRequestSerializeTest() {
        val updateRequest = CoinUpdateRequest(
            requestType = "create",
            requestId = "123",
            mode = CoinRequestDebugMode.TEST,
            coin = Coin(
                name = "г. Нижний Новгород, Нижегородская область",
                nominal = "10 рублей"
            )
        )

        val result = apiRequestSerialize(updateRequest)
        assertContains(result, """"name":"г. Нижний Новгород, Нижегородская область"""")
    }

    @Test
    fun deleteRequestSerializeTest() {
        val deleteRequest = CoinDeleteRequest(
            requestType = "create",
            requestId = "123",
            mode = CoinRequestDebugMode.TEST,
            cNum = "123"
        )

        val result = apiRequestSerialize(deleteRequest)
        assertContains(result, """"cNum":"123"""")
    }
}