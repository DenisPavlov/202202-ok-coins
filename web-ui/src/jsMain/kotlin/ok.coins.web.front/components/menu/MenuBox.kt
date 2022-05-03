package ok.coins.web.front.components.menu

import mui.material.Toolbar
import mui.system.Box
import mui.system.sx
import ok.coins.web.front.common.Sizes
import ok.coins.web.front.components.Page
import react.FC
import react.Props

external interface MenuBoxProps : Props {
    var data: List<Page>
    var lastPathname: String?
}

val MenuBox = FC<MenuBoxProps> { props ->
    Box {
        Toolbar()

        mui.material.List {
            sx { width = Sizes.Sidebar.width }

            for ((key, name) in props.data) {
                MenuRow {
                    linkTo = key
                    this.name = name
                    lastPathname = props.lastPathname
                }
            }
        }
    }
}