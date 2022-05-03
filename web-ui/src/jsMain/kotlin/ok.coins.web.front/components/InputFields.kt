package ok.coins.web.front.components

import csstype.px
import mui.material.FormControlVariant
import mui.material.TextField
import mui.system.sx
import org.w3c.dom.HTMLDivElement
import react.FC
import react.Props
import react.ReactNode
import react.dom.events.FormEvent
import react.dom.onChange

external interface InputProps : Props {
    var id: String
    var name: String
    var rows: Int?
    var onChange: (FormEvent<HTMLDivElement>) -> Unit
}

val FullWithInput = FC<InputProps> { props ->
    TextField {
        sx {
            padding = 5.px
        }
        fullWidth = true
        id = props.id
        label = ReactNode(props.name)
        variant = FormControlVariant.filled
        props.rows?.let {
            rows = it
            multiline = true
        }
        onChange = props.onChange
    }
}

val ShortWithInput = FC<InputProps> { props ->
    TextField {
        sx {
            padding = 5.px
        }
        id = props.id
        label = ReactNode(props.name)
        variant = FormControlVariant.filled
        onChange = props.onChange
    }
}
