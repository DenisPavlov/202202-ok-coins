package ok.coins.web.front.components

import csstype.JustifyContent
import csstype.pct
import csstype.px
import kotlinx.coroutines.launch
import mui.material.Box
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.FormControlVariant.filled
import mui.material.Paper
import mui.material.TextField
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import ok.coins.web.common.AddCoin
import ok.coins.web.front.addCoin
import ok.coins.web.front.scope
import react.ReactNode
import react.VFC
import react.dom.html.ButtonType
import react.dom.html.InputType
import react.dom.html.ReactHTML.form
import react.dom.onChange
import react.useState

val AddCoinForm = VFC {

    val (coin, setCoin) = useState(AddCoin())

    Paper {
        sx {
            width = 100.pct
            justifyContent = JustifyContent.left
        }
        Typography {
            sx {
                padding = 5.px
            }
            variant = TypographyVariant.h4
            +"Добавить монету"
        }

        form {
            onSubmit = {
                println(coin)
                scope.launch {
                    addCoin(coin)
                }
            }

            FullWithInput {
                name = "Серия"
                id = "series"
                onChange = { event ->
                    val series = event.target.asDynamic().value.toString()
                    setCoin {
                        it.copy(series = series)
                    }
                }
            }
            FullWithInput {
                name = "Название монеты"
                id = "name"
                onChange = { event ->
                    val name = event.target.asDynamic().value.toString()
                    setCoin {
                        it.copy(name = name)
                    }
                }
            }

            Box {
                TextField {
                    sx { padding = 5.px }
                    id = "date"
                    variant = filled
                    label = ReactNode("Дата выпуска")
                    type = InputType.date
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(releaseDate = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "c-num"
                    name = "Каталожный номер"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(cNum = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "nominal"
                    name = "Номинал"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(nominal = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "quality"
                    name = "Качество"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(quality = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "alloy"
                    name = "Сплав"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(alloy = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "weight"
                    name = "Масса общая, г"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(weight = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "diameter"
                    name = "Диаметр, мм"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(diameter = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "thickness"
                    name = "Толщина, мм"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(thickness = value)
                        }
                    }
                }
                ShortWithInput {
                    id = "all-count"
                    name = "Тираж, шт"
                    onChange = { event ->
                        val value = event.target.asDynamic().value.toString()
                        setCoin {
                            it.copy(allCount = value)
                        }
                    }
                }
            }

            FullWithInput {
                name = "Аверс"
                id = "averse"
                rows = 3
                onChange = { event ->
                    val value = event.target.asDynamic().value.toString()
                    setCoin {
                        it.copy(averse = value)
                    }
                }
            }
            FullWithInput {
                name = "Реверс"
                id = "reverse"
                rows = 3
                onChange = { event ->
                    val value = event.target.asDynamic().value.toString()
                    setCoin {
                        it.copy(reverse = value)
                    }
                }
            }
            FullWithInput {
                name = "Авторы"
                id = "authors"
                rows = 3
                onChange = { event ->
                    val value = event.target.asDynamic().value.toString()
                    setCoin {
                        it.copy(authors = value)
                    }
                }
            }
            FullWithInput {
                name = "Историко-тематическая справка"
                id = "info"
                rows = 6
                onChange = { event ->
                    val value = event.target.asDynamic().value.toString()
                    setCoin {
                        it.copy(info = value)
                    }
                }
            }

            Button {
                variant = ButtonVariant.contained
                +"Добавить"
                type = ButtonType.submit
                fullWidth = true
            }
        }
    }
}
