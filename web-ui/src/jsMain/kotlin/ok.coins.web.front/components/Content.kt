package ok.coins.web.front.components

import csstype.px
import mui.material.Typography
import mui.system.Box
import mui.system.sx
import ok.coins.web.front.common.Area
import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML.main
import react.router.Outlet
import react.router.Route
import react.router.Routes

private val DEFAULT_PADDING = 30.px

val Content = FC<Props> {
    Routes {
        Route {
            path = "/"
            element = Box.create {
                component = main
                sx {
                    gridArea = Area.content
                    padding = DEFAULT_PADDING
                }
                Outlet()
            }

            pages.forEach {
                Route {
                    path = it.key
                    element = it.element.create()
                }
            }

            Route {
                path = "*"
                element = Typography.create { +"404 Страница не найдена" }
            }
        }
    }
}
