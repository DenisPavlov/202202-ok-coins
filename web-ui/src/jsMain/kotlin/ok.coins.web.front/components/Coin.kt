package ok.coins.web.front.components

import csstype.TextAlign
import csstype.pct
import csstype.px
import mui.material.Button
import mui.material.Card
import mui.material.CardActions
import mui.material.CardContent
import mui.material.CardMedia
import mui.material.Size
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML

external interface CoinProps : Props {
    var img: String
    var nominal: String
    var name: String
    var releaseYear: String
}

val Coin = FC<CoinProps> { props ->
    Card {
        sx {
            margin = 12.px
            width = 200.px
            textAlign = TextAlign.center
            padding = 5.px
        }
        CardMedia {
            component = ReactHTML.img
            image = props.img
        }

        CardContent {
            Typography {
                asDynamic().color = "text.secondary"
                gutterBottom = true
                +props.name
            }
            Typography {
                component = ReactHTML.div
                variant = TypographyVariant.body1

                +"${props.nominal} / ${props.releaseYear}"
            }
        }
        CardActions {
            Button {
                sx {
                    width = 50.pct
                }
                size = Size.small
                +"Смотреть"
            }
            Button {
                sx {
                    width = 50.pct
                }
                size = Size.small
                +"Редактировать"
            }
        }
    }
}
