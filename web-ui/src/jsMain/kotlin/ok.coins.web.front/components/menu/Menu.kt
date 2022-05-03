package ok.coins.web.front.components.menu

import csstype.Position.Companion.absolute
import csstype.px
import mui.material.DrawerAnchor.left
import mui.material.SpeedDial
import mui.material.SwipeableDrawer
import mui.system.Box
import mui.system.sx
import ok.coins.web.front.components.pages
import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML.nav
import react.router.useLocation
import react.useState
import mui.icons.material.Menu as MenuIcon

val Menu = FC<Props> {
    var isOpen by useState(false)

    val lastPathname = useLocation().pathname.substringAfterLast("/")

    Box {
        component = nav

        SwipeableDrawer {
            anchor = left
            open = isOpen
            onOpen = { isOpen = true }
            onClose = { isOpen = false }

            MenuBox {
                this.lastPathname = lastPathname
                this.data = pages
            }
        }

        SpeedDial {
            sx {
                position = absolute
                bottom = 16.px
                left = 16.px
            }
            ariaLabel = "Menu"
            icon = MenuIcon.create()
            onClick = { isOpen = true }
        }
    }
}

