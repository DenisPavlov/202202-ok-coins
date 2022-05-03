package ok.coins.web.front.components.menu

import csstype.Color
import csstype.None
import emotion.react.css
import mui.material.ListItemButton
import mui.material.ListItemText
import react.FC
import react.Props
import react.ReactNode
import react.router.dom.NavLink

external interface MenuRowProps : Props {
    var linkTo: String
    var name: String
    var lastPathname: String?
}

val MenuRow = FC<MenuRowProps> { props ->
    NavLink {
        to = props.linkTo

        css {
            textDecoration = None.none
            color = Color.currentcolor
        }

        ListItemButton {
            selected = props.lastPathname == props.linkTo

            ListItemText {
                primary = ReactNode(props.name)
            }
        }
    }
}