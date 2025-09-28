package org.example.kobwebemptyproject

import com.varabyte.kobweb.compose.css.borderColor
import com.varabyte.kobweb.compose.css.transitionDuration
import org.jetbrains.compose.web.css.*

object AppStyles {
    lateinit var siteStyleSheet: SiteStyleSheet
}

class SiteStyleSheet(val sitePalette: SitePalette) : StyleSheet() {
    val regularButtonClass by style {
        backgroundColor(Color.transparent)
        border(
            width = 2.px,
            style = LineStyle.Solid,
            color = Color.dodgerblue
        )
        borderRadius(12.px)
        padding(8.px)
        textAlign("center")
        textDecoration("none")
        display(DisplayStyle.InlineBlock)
        fontSize(14.px)
        margin(4.px, 2.px)
        transitionDuration(0.4.s)
        cursor("pointer")

        // hover selector for a class
        self + hover style { // self is a selector for `container`
            backgroundColor(Color.dodgerblue)
            color(sitePalette.siteColorInverse)
        }
    }

    val regularButtonClassSelected by style {
        borderColor(Color.orange)
        // hover selector for a class
        self + hover style { // self is a selector for `container`
            backgroundColor(Color.orange)
        }
    }
}


