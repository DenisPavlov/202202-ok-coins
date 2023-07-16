package ru.otus.otuskotlin.coins.dsl.models

import java.time.LocalDate

data class Coin(
    var name: String,
    var series: String,
    var releaseDate: LocalDate,
    var cNum: CatalogNumber,
    var nominal: String,
    var material: String,
    var weight: String,
    var diameter: String,
    var thickness: String,
    var allCount: Int,
    var averse: String,
    var reverse: String,
    var authors: String,
    var info: String,
    var imgUri: String,
)
