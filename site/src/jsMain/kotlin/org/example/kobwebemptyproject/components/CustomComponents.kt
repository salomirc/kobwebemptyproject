package org.example.kobwebemptyproject.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

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

data class RegularButtonProperties(
    val text: String,
    val color: CSSColorValue,
    val backgroundColor: CSSColorValue
)

@Composable
fun RegularButton(
    properties: RegularButtonProperties,
    onClick: () -> Unit
) {
    Button(attrs ={
        onClick {
            onClick()
        }
        style {
            borderWidth(0.px)
            width(96.px)
            height(48.px)
            color(properties.color)
            backgroundColor(properties.backgroundColor)
        }
    }) {
        Text(properties.text)
    }
}

@Composable
fun IconButton(
    backgroundColor: CSSColorValue,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(attrs ={
        onClick {
            onClick()
        }
        style {
            borderWidth(0.px)
            backgroundColor(backgroundColor)
            width(96.px)
            height(48.px)
        }
    }) {
        Div(attrs = {
            style {
                fontSize(30.px)
            }
        }) {
            content()
        }
    }
}