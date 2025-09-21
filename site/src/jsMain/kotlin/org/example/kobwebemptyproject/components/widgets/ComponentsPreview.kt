package org.example.kobwebemptyproject.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.sections.Header
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Page("/preview")
@Composable
fun ComponentsPreview() {
    Header()
    Div(attrs = {
        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            alignItems(AlignItems.Center)
            padding(10.px)
        }
    }) {
        NumberBoxPreview()
        RegularButtonPreview()
        IconButtonPreview()
    }
}

@Composable
fun PreviewWrapper(
    vararg padding: CSSNumeric,
    content: @Composable () -> Unit
) {
    val sitePalette = ColorMode.current.toSitePalette()
    Div(attrs = {
        style {
            backgroundColor(sitePalette.overlayTransparent)
//            padding(*padding)
            padding(10.px)
            margin(10.px)
        }
    }) {
        content()
    }
}

@Composable
fun NumberBoxPreview() {
    PreviewWrapper(
        padding = arrayOf(10.px, 0.px)
    ) {
        NumberBox(
            text = "1"
        )
    }
}

@Composable
fun RegularButtonPreview() {
    PreviewWrapper(
        padding = arrayOf(36.px, 10.px)
    ) {
        RegularButton(
            text = "Click me!",
            onClick = {}
        )
    }
}

@Composable
fun IconButtonPreview() {
    var colorMode by ColorMode.currentState
    PreviewWrapper(
        padding = arrayOf(30.px, 10.px)
    ) {
        IconButton(
            backgroundColor = Color.dodgerblue,
            onClick = {},
            content = {
                if (colorMode.isLight) {
                    SunIcon(modifier = Modifier
                        .color(Color.black)
                        .padding(top = 8.px)
                    )
                } else {
                    MoonIcon(modifier = Modifier
                        .color(Color.white)
                        .padding(top = 8.px)
                    )
                }
            }
        )
    }
}