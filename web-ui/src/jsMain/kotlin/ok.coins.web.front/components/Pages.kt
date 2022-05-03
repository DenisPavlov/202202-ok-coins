package ok.coins.web.front.components

import react.ComponentType
import react.Props

data class Page(
    val key: String,
    val name: String,
    val element: ComponentType<Props>
)

val pages = listOf(
    Page("collection", "Моя коллекция", Collection),
    Page("add-coin", "Добавить монету", AddCoinForm),
)
