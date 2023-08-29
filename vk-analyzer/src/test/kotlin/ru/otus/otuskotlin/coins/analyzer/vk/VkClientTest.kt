package ru.otus.otuskotlin.coins.analyzer.vk

import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class VkClientTest {

    private val client = VkClient(
        token = TODO("put VK token"),
    )

    val groups = listOf(
        "numizmatika_of_russia",
        "banknotnik",
        "russkayamoneta",
        "brak_monet",
        "monety_rossii",
        "i_sell_coins",
        "aguru_pro",
    )

    @Test
    fun analyze(): Unit = runBlocking {
        groups.forEach {
            analyzeAndWriteFiles(groupId = it)
        }
    }

    private suspend fun analyzeAndWriteFiles(groupId: String) {
        val users = client.getAllUsers(groupId)

        users.groupBySex().writeCsv(
            groupId = groupId,
            keyName = "sex"
        )

        users.groupByAge().writeCsv(
            groupId = groupId,
            keyName = "age"
        )

        users.groupByCountry().writeCsv(
            groupId = groupId,
            keyName = "country"
        )

        users.groupByCity().writeCsv(
            groupId = groupId,
            keyName = "city"
        )
    }
}
