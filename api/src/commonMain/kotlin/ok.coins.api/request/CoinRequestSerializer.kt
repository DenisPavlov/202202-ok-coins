package ok.coins.api.request

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ru.otus.otuskotlin.coins.api.v1.IRequest

/**
 * Сериализатор для десериализации Json-строки по значению дескриминатора
 */
internal object CoinRequestSerializer : JsonContentPolymorphicSerializer<IRequest>(IRequest::class) {

    private const val discriminator = "requestType"

    override fun selectDeserializer(element: JsonElement): KSerializer<out IRequest> {
        return when (val discriminatorValue = element.jsonObject[discriminator]?.jsonPrimitive?.content) {
            "create" -> RequestSerializers.create
            "read" -> RequestSerializers.read
            "update" -> RequestSerializers.update
            "delete" -> RequestSerializers.delete
            else -> throw SerializationException(
                "Unknown value '${discriminatorValue}' in discriminator '$discriminator' " +
                        "property of IRequest implementation"
            )
        }
    }
}