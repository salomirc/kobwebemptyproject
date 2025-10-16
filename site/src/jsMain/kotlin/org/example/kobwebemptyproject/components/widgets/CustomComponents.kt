package org.example.kobwebemptyproject.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.AppStyles
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.type
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun NumberBox(
    text: String,
    style: StyleScope.() -> Unit = {}
) {
    val sitePalette = ColorMode.current.toSitePalette()
    Div(attrs = {
        style {
            backgroundColor(sitePalette.surfaceVariant)
            width(100.px)
            margin(10.px)
            textAlign("center")
            lineHeight(75.px)
            fontSize(30.px)
            style()
        }
    }) {
        Text(text)
    }
}

@Composable
fun RegularButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val styles: MutableList<String> = mutableListOf(AppStyles.siteStyleSheet.regularButtonClass).apply {
        if (isSelected) add(AppStyles.siteStyleSheet.regularButtonClassSelected)
    }
    Button(attrs ={
        type(ButtonType.Button)
        classes(styles)
        onClick {
            onClick()
        }
    }) {
        Text(text)
    }
}

@Composable
fun IconButton(
    styles: List<String>? = null,
    fontSize: CSSNumeric = 30.px,
    backgroundColor: CSSColorValue,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val styles: MutableList<String> = mutableListOf(AppStyles.siteStyleSheet.iconButtonClass).apply {
        styles?.let { this.addAll(it) }
    }
    Button(attrs ={
        onClick {
            onClick()
        }
        classes(styles)
        style {
            backgroundColor(backgroundColor)
        }
    }) {
        Div(attrs = {
            style {
                fontSize(fontSize)
            }
        }) {
            content()
        }
    }
}