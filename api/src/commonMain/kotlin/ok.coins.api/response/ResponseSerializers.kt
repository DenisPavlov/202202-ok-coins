package ok.coins.api.response

import ru.otus.otuskotlin.coins.api.v1.CoinCreateResponse
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteResponse
import ru.otus.otuskotlin.coins.api.v1.CoinReadResponse
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateResponse

internal object ResponseSerializers {
    val create = ResponseSerializer(CoinCreateResponse.serializer())
    val read   = ResponseSerializer(CoinReadResponse.serializer())
    val update = ResponseSerializer(CoinUpdateResponse.serializer())
    val delete = ResponseSerializer(CoinDeleteResponse.serializer())
}