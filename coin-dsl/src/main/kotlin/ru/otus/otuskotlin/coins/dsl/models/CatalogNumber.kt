package ru.otus.otuskotlin.coins.dsl.models

data class CatalogNumber(
    val section: Section,
    val metal: Metal,
    val nominal: Nominal,
    val serialNumber: String,
) {
    override fun toString() = "${section.number}${metal.number}${nominal.number.value}-$serialNumber"

    data class Section(val number: Int, val description: String)
    data class Metal(val number: Int, val description: String)
    data class Nominal(val number: Number, val description: String) {
        @JvmInline
        value class Number(val value: String) {
            init {
                check(value.length == 2)
                check(value[0].isDigit())
                check(value[1].isDigit())
            }
        }
    }
}
