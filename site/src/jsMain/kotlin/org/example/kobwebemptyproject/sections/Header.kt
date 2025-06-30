package org.example.kobwebemptyproject.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.IconButton
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div

@Composable
fun Header() {
    var colorMode by ColorMode.currentState
    Div(attrs = {
        id("header")
        style {
            width(100.percent)
            padding(20.px)
            textAlign("right")
        }
    }) {
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