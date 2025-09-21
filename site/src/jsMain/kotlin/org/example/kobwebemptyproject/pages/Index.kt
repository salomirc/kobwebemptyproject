package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.widgets.NumberBox
import org.example.kobwebemptyproject.components.sections.Header
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Page
@Composable
fun HomePage(ctx: PageContext) {
    var colorMode by ColorMode.currentState
    Div(attrs = {
        id("container")
    }) {
        Header()
        Div(attrs = {
            id("content")
            style {
                padding(10.px)
                display(DisplayStyle.Flex)
                flexWrap(FlexWrap.Wrap)
            }
        }) {
            repeat(100) { index ->
                NumberBox(
                    text = "$index"
                )
            }
        }
    }
}
