package ok.coins.api

import ok.coins.api.request.CoinRequestSerializer
import ru.otus.otuskotlin.coins.api.v1.IRequest

fun apiRequestSerialize(request: IRequest): String = serializationMapper.encodeToString(CoinRequestSerializer, request)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiRequestDeserialize(json: String): T =
    serializationMapper.decodeFromString(CoinRequestSerializer, json) as T