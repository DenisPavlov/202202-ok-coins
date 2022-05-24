package ok.coins.api

import ru.otus.otuskotlin.coins.api.v1.CoinCreateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteRequest
import ru.otus.otuskotlin.coins.api.v1.CoinReadRequest
import ru.otus.otuskotlin.coins.api.v1.CoinRequestDebugMode
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateRequest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DeserializationRequestTest {

    @Test
    fun createRequestDeserializeTest() {
        val jsonCreateRequest = """
            {
              "coin": {
                "name": "г. Нижний Новгород, Нижегородская область",
                "nominal": "10 рублей"
              },
              "requestType": "create",
              "requestId": "123",
              "mode": "prod"
            }
        """.trimIndent()

        val result = apiRequestDeserialize<CoinCreateRequest>(jsonCreateRequest)

        with(result) {
            assertEquals(requestType, "create")
            assertEquals(requestId, "123")
            assertEquals(mode, CoinRequestDebugMode.PROD)
            with(coin!!){
                assertEquals(name, "г. Нижний Новгород, Нижегородская область")
                assertEquals(nominal, "10 рублей")
            }
        }
    }

    @Test
    fun readRequestDeserializeTest() {
        val jsonReadRequest = """
            {
              "cNum": "cNum-1",
              "requestType": "read",
              "requestId": "requestId-1",
              "mode": "test"
            }
        """.trimIndent()

        val result = apiRequestDeserialize<CoinReadRequest>(jsonReadRequest)

        with(result) {
            assertEquals(cNum, "cNum-1")
            assertEquals(requestType, "read")
            assertEquals(mode, CoinRequestDebugMode.TEST)
        }
    }

    @Test
    fun updateRequestDeserializeTest() {
        val jsonCreateRequest = """
            {
              "coin": {
                "name": "г. Нижний Новгород, Нижегородская область",
                "nominal": "10 рублей"
              },
              "requestType": "update",
              "requestId": "123",
              "mode": "prod"
            }
        """.trimIndent()

        val result = apiRequestDeserialize<CoinUpdateRequest>(jsonCreateRequest)

        with(result) {
            assertEquals(requestType, "update")
            assertEquals(requestId, "123")
            assertEquals(mode, CoinRequestDebugMode.PROD)
            with(coin!!){
                assertEquals(name, "г. Нижний Новгород, Нижегородская область")
                assertEquals(nominal, "10 рублей")
            }
        }
    }

    @Test
    fun deleteRequestDeserializeTest() {
        val jsonReadRequest = """
            {
              "cNum": "cNum-1",
              "requestType": "delete",
              "requestId": "requestId-1",
              "mode": "test"
            }
        """.trimIndent()

        val result = apiRequestDeserialize<CoinDeleteRequest>(jsonReadRequest)

        with(result) {
            assertEquals(cNum, "cNum-1")
            assertEquals(requestType, "delete")
            assertEquals(mode, CoinRequestDebugMode.TEST)
        }
    }
}