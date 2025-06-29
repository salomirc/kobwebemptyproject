package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import org.example.kobwebemptyproject.components.IconButton
import org.example.kobwebemptyproject.components.NumberBox
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Page
@Composable
fun HomePage() {
    var isDarkMode by remember { mutableStateOf(false) }
    Div(attrs = {
        id("container")
        style {
            backgroundColor(if (isDarkMode) Color.black else Color.white)
        }
    }) {
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
                    isDarkMode = !isDarkMode
                },
                backgroundColor = Color.transparent,
                content = {
                    if (isDarkMode) {
                        SunIcon(modifier = Modifier
                            .color(Color.white)
                            .padding(top = 8.px)
                        )
                    } else {
                        MoonIcon(modifier = Modifier
                            .color(Color.black)
                            .padding(top = 8.px)
                        )
                    }
                }
            )
        }
        Div(attrs = {
            id("content")
            style {
                padding(10.px)
                display(DisplayStyle.Flex)
                flexWrap(FlexWrap.Wrap)
            }
        }) {
            repeat(100) { index ->
                NumberBox("$index")
            }
        }
    }
}
