package ok.coins.api

import ru.otus.otuskotlin.coins.api.v1.CoinCreateResponse
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteResponse
import ru.otus.otuskotlin.coins.api.v1.CoinReadResponse
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateResponse
import ru.otus.otuskotlin.coins.api.v1.ResponseResult
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DeserializationResponseTest {

    @Test
    fun createResponseDeserializeTest() {
        val jsonCreateRequest = """
            {
              "responseType": "create",
              "requestId": "requestId-1",
              "result": "error",
              "errors": [
                {
                  "code": "1",
                  "message": "Some error"
                }
              ],
              "coin": {
                "name": "г. Нижний Новгород, Нижегородская область",
                "nominal": "10 рублей"
              }
            }
        """.trimIndent()

        val result = apiResponseDeserialize<CoinCreateResponse>(jsonCreateRequest)

        with(result) {
            assertEquals(responseType, "create")
            assertEquals(requestId, "requestId-1")
            assertEquals(this.result, ResponseResult.ERROR)

            with(errors?.get(0)) {
                assertEquals(this?.code, "1")
                assertEquals(this?.message, "Some error")
            }

            with(coin!!) {
                assertEquals(name, "г. Нижний Новгород, Нижегородская область")
                assertEquals(nominal, "10 рублей")
            }
        }
    }

    @Test
    fun readResponseDeserializeTest() {
        val jsonCreateRequest = """
            {
              "responseType": "read",
              "requestId": "requestId-1",
              "result": "error",
              "errors": [
                {
                  "code": "1",
                  "message": "Some error"
                }
              ],
              "coin": {
                "name": "г. Нижний Новгород, Нижегородская область",
                "nominal": "10 рублей"
              }
            }
        """.trimIndent()

        val result = apiResponseDeserialize<CoinReadResponse>(jsonCreateRequest)

        with(result) {
            assertEquals(responseType, "read")
            assertEquals(requestId, "requestId-1")
            assertEquals(this.result, ResponseResult.ERROR)

            with(errors?.get(0)) {
                assertEquals(this?.code, "1")
                assertEquals(this?.message, "Some error")
            }

            with(coin!!) {
                assertEquals(name, "г. Нижний Новгород, Нижегородская область")
                assertEquals(nominal, "10 рублей")
            }
        }
    }

    @Test
    fun updateResponseDeserializeTest() {
        val jsonCreateRequest = """
            {
              "responseType": "update",
              "requestId": "requestId-1",
              "result": "error",
              "errors": [
                {
                  "code": "1",
                  "message": "Some error"
                }
              ],
              "coin": {
                "name": "г. Нижний Новгород, Нижегородская область",
                "nominal": "10 рублей"
              }
            }
        """.trimIndent()

        val result = apiResponseDeserialize<CoinUpdateResponse>(jsonCreateRequest)

        with(result) {
            assertEquals(responseType, "update")
            assertEquals(requestId, "requestId-1")
            assertEquals(this.result, ResponseResult.ERROR)

            with(errors?.get(0)) {
                assertEquals(this?.code, "1")
                assertEquals(this?.message, "Some error")
            }

            with(coin!!) {
                assertEquals(name, "г. Нижний Новгород, Нижегородская область")
                assertEquals(nominal, "10 рублей")
            }
        }
    }

    @Test
    fun deleteResponseDeserializeTest() {
        val jsonCreateRequest = """
            {
              "responseType": "delete",
              "requestId": "requestId-1",
              "result": "error",
              "errors": [
                {
                  "code": "1",
                  "message": "Some error"
                }
              ],
              "coin": {
                "name": "г. Нижний Новгород, Нижегородская область",
                "nominal": "10 рублей"
              }
            }
        """.trimIndent()

        val result = apiResponseDeserialize<CoinDeleteResponse>(jsonCreateRequest)

        with(result) {
            assertEquals(responseType, "delete")
            assertEquals(requestId, "requestId-1")
            assertEquals(this.result, ResponseResult.ERROR)

            with(errors?.get(0)) {
                assertEquals(this?.code, "1")
                assertEquals(this?.message, "Some error")
            }

            with(coin!!) {
                assertEquals(name, "г. Нижний Новгород, Нижегородская область")
                assertEquals(nominal, "10 рублей")
            }
        }
    }
}