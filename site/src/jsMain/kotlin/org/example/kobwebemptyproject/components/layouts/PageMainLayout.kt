package org.example.kobwebemptyproject.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.sections.NavHeader
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Layout
@Composable
fun PageMainLayout(content: @Composable () -> Unit) {
    val ctx: PageContext = rememberPageContext()
    var colorMode by ColorMode.currentState

    NavHeader(ctx)
    Div(attrs = {
        id("underHeaderDecoration")
        style {
            backgroundColor(colorMode.toSitePalette().brand.primary)
            width(100.percent)
            height(4.px)
        }
    })
    content()
}