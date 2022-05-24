package ok.coins.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import ok.coins.api.request.CoinRequestSerializer
import ok.coins.api.request.RequestSerializers
import ok.coins.api.response.CoinResponseSerializer
import ok.coins.api.response.ResponseSerializers
import ru.otus.otuskotlin.coins.api.v1.CoinCreateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinCreateResponse
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteRequest
import ru.otus.otuskotlin.coins.api.v1.CoinDeleteResponse
import ru.otus.otuskotlin.coins.api.v1.CoinReadRequest
import ru.otus.otuskotlin.coins.api.v1.CoinReadResponse
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateRequest
import ru.otus.otuskotlin.coins.api.v1.CoinUpdateResponse
import ru.otus.otuskotlin.coins.api.v1.IRequest
import ru.otus.otuskotlin.coins.api.v1.IResponse

/**
 * Объект настроенного kotlinx json-мапера
 */
@OptIn(ExperimentalSerializationApi::class)
internal val serializationMapper = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true

    serializersModule = SerializersModule {
        polymorphicDefaultSerializer(IRequest::class) { instance ->
            @Suppress("UNCHECKED_CAST")
            when (instance) {
                is CoinCreateRequest -> RequestSerializers.create as SerializationStrategy<IRequest>
                is CoinReadRequest -> RequestSerializers.read as SerializationStrategy<IRequest>
                is CoinUpdateRequest -> RequestSerializers.update as SerializationStrategy<IRequest>
                is CoinDeleteRequest -> RequestSerializers.delete as SerializationStrategy<IRequest>
                else -> null
            }
        }
        polymorphicDefault(IRequest::class) {
            CoinRequestSerializer
        }
        polymorphicDefaultSerializer(IResponse::class) { instance ->
            @Suppress("UNCHECKED_CAST")
            when (instance) {
                is CoinCreateResponse -> ResponseSerializers.create as SerializationStrategy<IResponse>
                is CoinReadResponse -> ResponseSerializers.read as SerializationStrategy<IResponse>
                is CoinUpdateResponse -> ResponseSerializers.update as SerializationStrategy<IResponse>
                is CoinDeleteResponse -> ResponseSerializers.delete as SerializationStrategy<IResponse>
                else -> null
            }
        }
        polymorphicDefault(IResponse::class) {
            CoinResponseSerializer
        }
    }
}
