package ok.coins.api.request

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encoding.Encoder
import ru.otus.otuskotlin.coins.api.v1.CoinCreateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteRequest
import ru.otus.otuskotlin.coins.api.v1.CoinReadRequest
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateRequest
import ru.otus.otuskotlin.coins.api.v1.IRequest

internal class RequestSerializer<T: IRequest>(private val serializer: KSerializer<T>): KSerializer<T> by serializer {
    override fun serialize(encoder: Encoder, value: T) {
        val request = when(value) {
            is CoinCreateRequest -> value.copy(requestType = "create")
            is CoinReadRequest -> value.copy(requestType = "read")
            is CoinUpdateRequest -> value.copy(requestType = "update")
            is CoinDeleteRequest -> value.copy(requestType = "delete")
            else -> throw SerializationException(
                "Unknown class to serialize as IRequest instance in RequestSerializer"
            )
        }
        @Suppress("UNCHECKED_CAST")
        return serializer.serialize(encoder, request as T)
    }
}
