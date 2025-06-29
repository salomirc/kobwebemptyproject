package org.example.kobwebemptyproject.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Page("/preview")
@Composable
fun ComponentsPreview() {
    Div(attrs = {
        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
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
    Div(attrs = {
        style {
            backgroundColor(Color.lightgray)
            width(120.px)
            height(120.px)
            padding(*padding)
            margin(10.px)
        }
    }) {
        content()
    }
}

@Composable
fun NumberBoxPreview() {
    PreviewWrapper(
        padding = arrayOf(10.px, 0.px, 0.px, 0.px)
    ) {
        NumberBox("1")
    }
}

@Composable
fun RegularButtonPreview() {
    PreviewWrapper(
        padding = arrayOf(35.px, 0.px, 0.px, 10.px)
    ) {
        var isDarkMode by remember { mutableStateOf(false) }
        RegularButton(
            properties = if (isDarkMode) {
                RegularButtonProperties(
                    text = "LightMode",
                    color = Color.white,
                    backgroundColor = Color.dodgerblue
                )
            } else {
                RegularButtonProperties(
                    text = "DarkMode",
                    color = Color.black,
                    backgroundColor = Color.dodgerblue
                )
            },
            onClick = {
                isDarkMode = !isDarkMode
            }
        )
    }
}

@Composable
fun IconButtonPreview() {
    var isDarkMode by remember { mutableStateOf(false) }
    PreviewWrapper(
        padding = arrayOf(35.px, 0.px, 0.px, 10.px)
    ) {
        IconButton(
            onClick = {
                isDarkMode = !isDarkMode
            },
            backgroundColor = Color.dodgerblue,
            content = {
                if (isDarkMode) {
                    MoonIcon(modifier = Modifier
                        .color(Color.white)
                        .padding(top = 8.px)
                    )
                } else {
                    SunIcon(modifier = Modifier
                        .color(Color.black)
                        .padding(top = 8.px)
                    )
                }
            }
        )
    }
}