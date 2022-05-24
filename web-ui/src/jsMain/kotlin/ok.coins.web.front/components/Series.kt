package ok.coins.web.front.components

import csstype.Display
import csstype.FlexWrap
import kotlinx.coroutines.launch
import mui.icons.material.ExpandMore
import mui.material.Accordion
import mui.material.AccordionDetails
import mui.material.AccordionSummary
import mui.material.Container
import mui.material.Typography
import mui.system.sx
import ok.coins.web.front.getCoinsBySeriesId
import ok.coins.web.front.scope
import react.FC
import react.Props
import react.create
import react.dom.aria.ariaControls
import react.useState
import ru.otus.otuskotlin.coins.api.v1.BaseCoin

external interface SeriesProps : Props {
    var name: String
    var id: String
}

val Series = FC<SeriesProps> { props ->

    var coins by useState(emptyList<BaseCoin>())

    Accordion {
        onChange = { _, _ ->
            if (coins.isEmpty()) {
                scope.launch {
                    coins = getCoinsBySeriesId(props.id)
                }
            }
        }

        AccordionSummary {
            id = "panel1a-header"
            ariaControls = "panel1a-content"
            expandIcon = ExpandMore.create()

            Typography {
                +props.name
            }
        }
        AccordionDetails {
            Container {
                maxWidth = false
                sx {
                    display = Display.flex
                    flexWrap = FlexWrap.wrap
                }

                coins.forEach {
                    Coin {
                        img = it.img!!
                        name = it.name!!
                        nominal = it.nominal!!
                        releaseYear = it.releaseDate!!
                    }
                }
            }
        }
    }
}