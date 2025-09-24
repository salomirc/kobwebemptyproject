package org.example.kobwebemptyproject.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.VerticalAlign
import com.varabyte.kobweb.compose.css.verticalAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.widgets.IconButton
import org.example.kobwebemptyproject.components.widgets.RegularButton
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
fun NavHeader(ctx: PageContext) {
    var colorMode by ColorMode.currentState

    Div(attrs = {
        id("header")
        style {
            backgroundColor(colorMode.toSitePalette().nearBackground)
            width(100.percent)
            padding(4.px)
            textAlign("right")
            verticalAlign(VerticalAlign.Top)
        }
    }) {
        RegularButton(
            text = "Home",
            onClick = {
                ctx.router.navigateTo("/")
            }
        )
        RegularButton(
            text = "About",
            onClick = {
                ctx.router.navigateTo("/about")
            }
        )
        RegularButton(
            text = "Preview",
            onClick = {
                ctx.router.navigateTo("/preview")
            }
        )
        IconButton(
            onClick = {
                colorMode = colorMode.opposite
            },
            backgroundColor = Color.transparent,
            content = {
                val color = colorMode.toSitePalette().brand.accent
                if (colorMode.isLight) {
                    MoonIcon(
                        modifier = Modifier
//                            .color(color)
                            .padding(top = 8.px)
                    )
                } else {
                    SunIcon(
                        modifier = Modifier
//                            .color(color)
                            .padding(top = 8.px)
                    )
                }
            }
        )
    }
}