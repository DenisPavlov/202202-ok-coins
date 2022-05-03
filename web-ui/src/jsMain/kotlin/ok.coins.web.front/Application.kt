package ok.coins.web.front

import csstype.Auto
import csstype.Display
import csstype.GridTemplateAreas
import csstype.array
import mui.material.useMediaQuery
import mui.system.Box
import mui.system.sx
import ok.coins.web.front.common.Area
import ok.coins.web.front.common.Sizes
import ok.coins.web.front.components.Content
import ok.coins.web.front.components.Header
import ok.coins.web.front.components.ThemeModule
import ok.coins.web.front.components.menu.Menu
import ok.coins.web.front.components.menu.Sidebar
import react.VFC
import react.router.dom.HashRouter

val Application = VFC {
    val mobileMode = useMediaQuery("(max-width:960px)")

    HashRouter {
        ThemeModule {
            Box {
                sx {
                    display = Display.grid
                    gridTemplateRows = array(
                        Sizes.Header.height,
                        Auto.auto,
                    )
                    gridTemplateColumns = array(Sizes.Sidebar.width, Auto.auto)

                    gridTemplateAreas = GridTemplateAreas(
                        arrayOf(Area.header, Area.header),
                        if (mobileMode)
                            arrayOf(Area.content, Area.content)
                        else
                            arrayOf(Area.sidebar, Area.content),
                    )
                }

                Header()
                if (mobileMode) Menu() else Sidebar()
                Content()
            }
        }
    }
}
