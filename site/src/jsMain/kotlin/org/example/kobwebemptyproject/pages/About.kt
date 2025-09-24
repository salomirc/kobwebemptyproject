package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.navigation.BasePath
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
    H3(attrs = {
        style {
            paddingLeft(10.px)
        }
    }) {
        Text("About Us")
    }
    Div(attrs = {
        id("imageContainer")
        style {
            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            height(50.vh)
            paddingBottom(20.px)
        }
    }) {
        Img(
            src = BasePath.prependTo("/images/androidify_me.png"),
            attrs = {
                style {
                    maxHeight(100.percent)
                    maxWidth(80.percent)
                    borderRadius(10.percent)
                }
            }
        )
    }
}