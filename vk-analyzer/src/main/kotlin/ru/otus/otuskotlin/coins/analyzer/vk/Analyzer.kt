package ru.otus.otuskotlin.coins.analyzer.vk

import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("FileWriterLogger")

fun List<User>.groupBySex(): Map<String, Int> {
    logger.info("Map users by sex")
    return groupBy {
        it.sex
    }.mapKeys { (key, _) ->
        when (key) {
            1 -> "female"
            2 -> "male"
            else -> "unknown"
        }
    }.mapValues {
        it.value.count()
    }
}

fun List<User>.groupByAge(): Map<String, Int> {
    logger.info("Map users by age")
    return mapNotNull {
        it.bdate
    }.mapNotNull {
        it.split(".").getOrNull(2)
    }.groupBy {
        it
    }.mapValues {
        it.value.count()
    }.toSortedMap()
}

fun List<User>.groupByCountry(): Map<String, Int> {
    logger.info("Map users by country")
    return mapNotNull {
        it.country?.title
    }.groupBy {
        it
    }.mapValues {
        it.value.count()
    }
}

fun List<User>.groupByCity(): Map<String, Int> {
    logger.info("Map users by city")
    return mapNotNull {
        it.city?.title
    }.groupBy {
        it
    }.mapValues {
        it.value.count()
    }
}
