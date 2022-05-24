package ok.coins.api.request

import ru.otus.otuskotlin.coins.api.v1.CoinCreateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteRequest
import ru.otus.otuskotlin.coins.api.v1.CoinReadRequest
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateRequest

internal object RequestSerializers {
    val create = RequestSerializer(CoinCreateRequest.serializer())
    val read = RequestSerializer(CoinReadRequest.serializer())
    val update = RequestSerializer(CoinUpdateRequest.serializer())
    val delete = RequestSerializer(CoinDeleteRequest.serializer())
}
