package ok.coins.api

import ok.coins.api.response.CoinResponseSerializer
import ru.otus.otuskotlin.coins.api.v1.IResponse

fun apiResponseSerialize(response: IResponse): String =
    serializationMapper.encodeToString(CoinResponseSerializer, response)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiResponseDeserialize(json: String): T =
    serializationMapper.decodeFromString(CoinResponseSerializer, json) as T
