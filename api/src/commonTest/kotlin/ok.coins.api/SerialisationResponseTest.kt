package ok.coins.api

import ru.otus.otuskotlin.coins.api.v1.CoinCreateResponse
import ru.otus.otuskotlin.coins.api.v1.ResponseResult
import kotlin.test.Test
import kotlin.test.assertContains

internal class SerialisationResponseTest {

    @Test
    fun createResponseSerializeTest() {
        val createResponse = CoinCreateResponse(
            responseType = "create",
            requestId = "requestId-1",
            result = ResponseResult.SUCCESS,
        )

        val result = apiResponseSerialize(createResponse)
        assertContains(result, """"result":"success"""")
    }

    @Test
    fun readResponseSerializeTest() {
        val readResponse = CoinCreateResponse(
            responseType = "read",
            requestId = "requestId-1",
            result = ResponseResult.SUCCESS,
        )

        val result = apiResponseSerialize(readResponse)
        assertContains(result, """"result":"success"""")
    }

    @Test
    fun updateResponseSerializeTest() {
        val updateResponse = CoinCreateResponse(
            responseType = "update",
            requestId = "requestId-1",
            result = ResponseResult.SUCCESS,
        )

        val result = apiResponseSerialize(updateResponse)
        assertContains(result, """"result":"success"""")
    }

    @Test
    fun deleteResponseSerializeTest() {
        val deleteResponse = CoinCreateResponse(
            responseType = "delete",
            requestId = "requestId-1",
            result = ResponseResult.SUCCESS,
        )

        val result = apiResponseSerialize(deleteResponse)
        assertContains(result, """"result":"success"""")
    }
}