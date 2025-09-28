package org.example.kobwebemptyproject.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.widgets.IconButton
import org.example.kobwebemptyproject.components.widgets.RegularButton
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun NavHeader(ctx: PageContext) {
    var colorMode by ColorMode.currentState

    Row(
        modifier = Modifier
            .id("header")
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .width(100.percent)
            .padding(all = 4.px),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
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
        RegularButton(
            text = "CustomBackendDemo",
            onClick = {
                ctx.router.navigateTo("/custom-backend-demo")
            }
        )
        Box(modifier = Modifier.width(20.px))
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