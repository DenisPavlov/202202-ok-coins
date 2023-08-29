package ru.otus.otuskotlin.coins.analyzer.vk

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory


private val logger = LoggerFactory.getLogger(VkClient::class.qualifiedName)

class VkClient(
    private val token: String,
) {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging)
    }

    suspend fun getAllUsers(
        groupId: String,
        count: Int = 1000,
        fields: List<String> = listOf("bdate", "city", "country", "sex"),
    ): List<User> {
        logger.info("Get all users for '$groupId' group")

        val members = getGroupMembers(groupId, count, fields)

        val amountOfUsers = members.response.count
        logger.info("all users in '$groupId' group are '$amountOfUsers'")

        val lastPartOfUsers = (count..amountOfUsers step 1000).map {
            getGroupMembers(groupId, count, fields, offset = it).response.items
        }.flatten()

        return (members.response.items + lastPartOfUsers).also {
            logger.info("Can get ${it.size} users")
        }
    }

    private suspend fun getGroupMembers(
        groupId: String,
        count: Int,
        fields: List<String>,
        offset: Int? = null,
    ): GetMembersResponse = client.get("https://api.vk.com/method/groups.getMembers") {
        parameter("group_id", groupId)
        parameter("fields", fields.joinToString(separator = ","))
        parameter("access_token", token)
        parameter("v", "5.131")
        parameter("count", count)
        offset?.let { parameter("offset", offset) }
    }.body()
}
