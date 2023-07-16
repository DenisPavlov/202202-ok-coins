package ru.otus.otuskotlin.coins.dsl

import ru.otus.otuskotlin.coins.dsl.models.CatalogNumber
import ru.otus.otuskotlin.coins.dsl.models.Coin
import java.time.LocalDate

@CoinDsl
fun coin(block: CoinContext.() -> Unit): Coin {
    val context = CoinContext().apply(block)
    return context.build()
}

class CoinContext {
    var series = ""
    var name = ""
    var catalogNumber = ""
    var allCount = 0
    var averse = ""
    var reverse = ""
    var imageUrl = ""

    private var releaseDate: LocalDate = LocalDate.MIN

    @CoinDsl
    fun releaseDate(block: ReleaseDateContext.() -> Unit) {
        val releaseDateContext = ReleaseDateContext().apply(block)
        releaseDate = releaseDateContext.build()
    }

    private var weight = ""

    @CoinDsl
    fun weight(block: OneErrorMeasureContext.() -> Unit) {
        val weightContext = OneErrorMeasureContext().apply(block)
        weight = weightContext.build()
    }

    private var diameter = ""

    @CoinDsl
    fun diameter(block: DiameterContext.() -> Unit) {
        val diameterContext = DiameterContext().apply(block)
        diameter = diameterContext.build()
    }

    private var thickness = ""

    @CoinDsl
    fun thickness(block: OneErrorMeasureContext.() -> Unit) {
        val thicknessContext = OneErrorMeasureContext().apply(block)
        thickness = thicknessContext.build()
    }

    private var authors = ""

    @CoinDsl
    fun authors(block: AuthorsContext.() -> Unit) {
        val authorsContext = AuthorsContext().apply(block)
        authors = authorsContext.build()
    }

    private var info = ""

    @CoinDsl
    fun info(block: InfoContext.() -> Unit) {
        val infoContext = InfoContext().apply(block)
        info = infoContext.build()
    }

    fun build(): Coin {

        val cNum = catalogNumber.toCatalogNumber()

        return Coin(
            name = name,
            series = series,
            releaseDate = releaseDate,
            cNum = cNum,
            nominal = cNum.nominal.description,
            material = cNum.metal.description,
            weight = weight,
            diameter = diameter,
            thickness = thickness,
            allCount = allCount,
            averse = averse.flatRowString(),
            reverse = reverse.flatRowString(),
            authors = authors,
            info = info,
            imgUri = imageUrl,
        )
    }
}

class ReleaseDateContext {
    var day = 0
    var month = ""
    var year = 0

    fun build(): LocalDate = LocalDate.of(
        year,
        russianMonthsToNumberMap[month] ?: throw RuntimeException("Can't get month number for '$month'"),
        day,
    )

    private val russianMonthsToNumberMap = mapOf(
        "январь" to 1,
        "февраль" to 2,
        "март" to 3,
        "апрель" to 4,
        "май" to 5,
        "июнь" to 6,
        "июль" to 7,
        "август" to 8,
        "сентябрь" to 9,
        "октябрь" to 10,
        "ноябрь" to 11,
        "декабрь" to 12,
    )
}

class OneErrorMeasureContext {
    var value = 0.0
    var measure = ""
    var measurementError = 0.0

    fun build() = "${value.format(2)} (±${measurementError.format(2)}) $measure."
}

class DiameterContext {
    var value = 0.0
    var measure = ""
    var measurementErrorPlus = 0.0
    var measurementErrorMinus = 0.0

    fun build() =
        "${value.format(2)} (+${measurementErrorPlus.format(2)}) (–${measurementErrorMinus.format(2)}) $measure."
}

class AuthorsContext {
    private val authorsList = mutableListOf<String>()

    operator fun String.unaryPlus() = authorsList.add(this)

    fun build(): String = authorsList.joinToString(separator = "\n")
}

class InfoContext {
    private val paragraphs = mutableListOf<String>()

    @CoinDsl
    fun paragraph(block: ParagraphContext.() -> Unit) {
        val paragraphContext = ParagraphContext().apply(block)
        paragraphs.add(paragraphContext.build())
    }

    fun build(): String =
        paragraphs.joinToString(separator = "\n\n", transform = String::flatRowString)
}

class ParagraphContext {
    private var paragraph = ""

    operator fun String.unaryPlus() {
        paragraph = this
    }

    fun build() = paragraph
}

private fun String.toCatalogNumber(): CatalogNumber {
    check(this.length == 9)

    val sectionIndex = this[0].digitToInt()
    val section = CatalogNumber.Section(sectionIndex, sectionsMap[sectionIndex]!!)

    val metalNumber = this[1].digitToInt()
    val metal = CatalogNumber.Metal(number = metalNumber, description = metalMap[metalNumber]!!)

    val nominalNumber = this.substring(2..3)
    val nominal = CatalogNumber.Nominal(
        number = CatalogNumber.Nominal.Number(nominalNumber),
        description = nominalMap[nominalNumber]!!,
    )

    return CatalogNumber(
        section = section,
        metal = metal,
        nominal = nominal,
        serialNumber = this.substring(5..8),
    )
}

private fun Double.format(scale: Int) = "%.${scale}f".format(this)

private fun String.flatRowString() = this.replace(oldValue = "\n", newValue = "", ignoreCase = true)

@DslMarker
annotation class CoinDsl
