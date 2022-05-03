package ok.coins.web.front

import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() = createRoot(document.createElement("div").also { document.body!!.appendChild(it) })
    .render(Application.create())
