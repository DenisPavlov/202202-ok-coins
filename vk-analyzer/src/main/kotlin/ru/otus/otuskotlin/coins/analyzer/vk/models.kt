package ru.otus.otuskotlin.coins.analyzer.vk

import kotlinx.serialization.Serializable

@Serializable
data class GetMembersResponse(
    val response: ResponseData,
)

@Serializable
data class ResponseData(
    val count: Int,
    val items: List<User>,
)

@Serializable
data class User(
    val sex: Int,
    val bdate: String? = null,
    val country: Country? = null,
    val city: City? = null,
)

@Serializable
data class Country(
    val id: Int,
    val title: String,
)

@Serializable
data class City(
    val id: Int,
    val title: String,
)
