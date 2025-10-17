package org.example.kobwebemptyproject

import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.css.borderColor
import com.varabyte.kobweb.compose.css.textTransform
import com.varabyte.kobweb.compose.css.transitionDuration
import org.jetbrains.compose.web.css.*

object AppStyles {
    lateinit var siteStyleSheet: SiteStyleSheet
}

class SiteStyleSheet(val sitePalette: SitePalette) : StyleSheet() {
    val navBarContainer by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Row)
        justifyContent(JustifyContent.End)
        alignItems(AlignItems.Center)
        padding(4.px)

        // media query
        media(
            query = screenMaxWidth640pxMediaQuery
        ) {
            self style {
                justifyContent(JustifyContent.SpaceBetween)
            }
        }
    }

    val barsMenuClass by style {
        display(DisplayStyle.None)
        // media query
        media(
            query = screenMaxWidth640pxMediaQuery
        ) {
            self style {
                display(DisplayStyle.Block)
            }
        }
    }

    val mobileMenu by style {
        // media query
        media(
            query = screenMinWidth640pxMediaQuery
        ) {
            self style {
                display(DisplayStyle.None)
            }
        }
    }

    val mobileMenuLink by style {
        textDecoration("none")
        textTransform(TextTransform.Uppercase)
    }

    val iconButtonClass by style {
        borderWidth(0.px)
        padding(8.px, 16.px)
        margin(1.px)
        cursor("pointer")
    }

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
        fontSize(14.px)
        margin(4.px, 2.px)
        transitionDuration(0.4.s)
        cursor("pointer")

        // hover selector for a class
        self + hover style { // self is a selector for `container`
            backgroundColor(Color.dodgerblue)
            color(sitePalette.siteColorInverse)
        }
        // media query
        media(
            query = screenMaxWidth640pxMediaQuery
        ) {
            self style {
                display(DisplayStyle.None)
            }
        }
    }

    val regularButtonClassSelected by style {
        borderColor(Color.orange)
        // hover selector for a class
        self + hover style { // self is a selector for `container`
            backgroundColor(Color.orange)
        }
    }

    val displayNone by style {
        display(DisplayStyle.None)
    }

    companion object {
        val <TBuilder> GenericStyleSheetBuilder<TBuilder>.screenMaxWidth640pxMediaQuery: CSSMediaQuery
            get() = CSSMediaQuery.MediaType(CSSMediaQuery.MediaType.Enum.Screen)
                .and(mediaMaxWidth(449.px))

        val <TBuilder> GenericStyleSheetBuilder<TBuilder>.screenMinWidth640pxMediaQuery: CSSMediaQuery
            get() = CSSMediaQuery.MediaType(CSSMediaQuery.MediaType.Enum.Screen)
                .and(mediaMinWidth(450.px))
    }
}


