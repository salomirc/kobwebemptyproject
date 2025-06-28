package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    var isDarkMode by remember { mutableStateOf(false) }
    // TODO: Replace the following with your own content
    Div(attrs = {
            style {
                padding(64.px)
                display(DisplayStyle.Flex)
                flexWrap(FlexWrap.Wrap)
                backgroundColor(if (isDarkMode) Color.black else Color.white)
            }
    }) {
        Div(attrs = {
            style {
                width(100.percent)
                textAlign("right")
            }
        }) {
            RegularButton(
                text = if (isDarkMode) "LightMode" else "DarkMode",
                isDarkMode = isDarkMode,
                onClick = {
                    isDarkMode = !isDarkMode
                }
            )
        }
        repeat(100) { index ->
            NumberBox("$index")
        }
    }
}


@Composable
fun NumberBox(text: String) {
    Div(attrs = {
        style {
            backgroundColor(Color.yellow)
            width(100.px)
            margin(10.px)
            textAlign("center")
            lineHeight(75.px)
            fontSize(30.px)
        }
    }) {
        Text(text)
    }
}

@Composable
fun RegularButton(
    text: String,
    isDarkMode: Boolean,
    onClick: () -> Unit
) {
    Button(attrs ={
        onClick {
            onClick()
        }
        style {
            backgroundColor(Color.dodgerblue)
            color(if (isDarkMode) Color.white else Color.black)
            borderWidth(0.px)
            width(100.px)
            height(100.px)
        }
    }) {
        Text(text)
    }
}



@Page("/preview/numberBox")
@Composable
fun numberBoxPreview() {
    NumberBox("1")
}

@Page("/preview/regularButton")
@Composable
fun regularButtonPreview() {
    RegularButton(
        text = "Click me!",
        isDarkMode = true,
        onClick = {}
    )
}
