package ok.coins.web.front.components

import csstype.integer
import csstype.number
import kotlinx.browser.window
import mui.icons.material.Brightness4
import mui.icons.material.Brightness7
import mui.icons.material.GitHub
import mui.material.AppBar
import mui.material.AppBarPosition
import mui.material.IconButton
import mui.material.IconButtonColor
import mui.material.Size
import mui.material.Switch
import mui.material.Toolbar
import mui.material.Tooltip
import mui.material.Typography
import mui.material.styles.TypographyVariant.h6
import mui.system.sx
import ok.coins.web.front.common.Area
import ok.coins.web.front.common.Themes
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.dom.aria.AriaHasPopup.`false`
import react.dom.aria.ariaHasPopup
import react.dom.aria.ariaLabel
import react.dom.html.ReactHTML.div
import react.useContext

val Header = FC<Props> {
    var theme by useContext(ThemeContext)

    AppBar {
        position = AppBarPosition.fixed
        sx {
            gridArea = Area.header
            zIndex = integer(1_500)
        }

        Toolbar {
            Typography {
                sx { flexGrow = number(1.0) }
                variant = h6
                noWrap = true
                component = div

                +"CoinsBook"
            }

            Tooltip {
                title = ReactNode("Тема")

                Switch {
                    icon = Brightness7.create()
                    checkedIcon = Brightness4.create()
                    checked = theme == Themes.Dark
                    ariaLabel = "theme"

                    onChange = { _, checked ->
                        theme = if (checked) Themes.Dark else Themes.Light
                    }
                }
            }

            Tooltip {
                title = ReactNode("Посмотреть исходники")

                IconButton {
                    ariaLabel = "source code"
                    ariaHasPopup = `false`
                    size = Size.large
                    color = IconButtonColor.inherit
                    onClick = {
                        window.location.href = "https://github.com/DenisPavlov/202202-ok-coins"
                    }

                    GitHub()
                }
            }
        }
    }
}
