package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
@Layout(".components.layouts.PageMainLayout")
fun AboutPage(ctx: PageContext) {
    var colorMode by ColorMode.currentState
    H3 {
        Text("About Us")
    }
    Div(attrs = {
        id("imageContainer")
        style {
            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
    }) {
        Img(
            src = "/kobwebemptyproject/images/androidify_me.png",
            attrs = {
                style {
                    minWidth(250.px)
                    width(25.percent)
                    borderRadius(20.px)
                }
            }
        )
    }
}