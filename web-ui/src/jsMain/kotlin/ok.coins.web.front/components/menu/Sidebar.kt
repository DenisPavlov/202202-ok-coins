package ok.coins.web.front.components.menu

import mui.material.Drawer
import mui.material.DrawerAnchor.left
import mui.material.DrawerVariant.permanent
import mui.system.Box
import mui.system.sx
import ok.coins.web.front.common.Area
import ok.coins.web.front.components.pages
import react.FC
import react.Props
import react.dom.html.ReactHTML.nav
import react.router.useLocation

val Sidebar = FC<Props> {
    val lastPathname = useLocation().pathname.substringAfterLast("/")

    Box {
        component = nav
        sx { gridArea = Area.sidebar }

        Drawer {
            variant = permanent
            anchor = left

            MenuBox {
                this.lastPathname = lastPathname
                data = pages
            }
        }
    }
}
