package ok.coins.api.response

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encoding.Encoder
import ru.otus.otuskotlin.coins.api.v1.CoinCreateResponse
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteResponse
import ru.otus.otuskotlin.coins.api.v1.CoinReadResponse
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateResponse
import ru.otus.otuskotlin.coins.api.v1.IResponse

internal class ResponseSerializer<T: IResponse>(private val serializer: KSerializer<T>): KSerializer<T> by serializer {
    override fun serialize(encoder: Encoder, value: T) {
        val request = when(value) {
            is CoinCreateResponse -> value.copy(responseType = "create")
            is CoinReadResponse -> value.copy(responseType = "read")
            is CoinUpdateResponse -> value.copy(responseType = "update")
            is CoinDeleteResponse -> value.copy(responseType = "delete")
            else -> throw SerializationException(
                "Unknown class to serialize as IRequest instance in RequestSerializer"
            )
        }
        @Suppress("UNCHECKED_CAST")
        return serializer.serialize(encoder, request as T)
    }
}
