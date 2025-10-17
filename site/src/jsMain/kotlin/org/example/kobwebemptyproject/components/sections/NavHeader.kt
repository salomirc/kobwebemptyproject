package org.example.kobwebemptyproject.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.borderBottom
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.icons.fa.FaIcon
import com.varabyte.kobweb.silk.components.icons.fa.IconCategory
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.AppStyles
import org.example.kobwebemptyproject.components.widgets.IconButton
import org.example.kobwebemptyproject.components.widgets.RegularButton
import org.example.kobwebemptyproject.models.ui.NavItem
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun NavHeader() {
    val ctx: PageContext = rememberPageContext()
    var colorMode by ColorMode.currentState
    val currentPath = ctx.route.path

    val navItems = remember {
        listOf(
            NavItem(title = "Home", target = "/"),
            NavItem(title = "About", target = "/about"),
            NavItem(title = "Preview", target = "/preview"),
            NavItem(title = "Backend", target = "/custom-backend-demo"),
        )
    }

    var selectedButton by remember { mutableStateOf(navItems[0]) }
    var isMobileMenu by remember { mutableStateOf(false) }

    LaunchedEffect(currentPath) {
        console.log("ctx.route.path = $currentPath")
        navItems.find { BasePath.prependTo(it.target) == currentPath }?.let { selectedButton = it }
    }

    Div(attrs = {
        classes(AppStyles.siteStyleSheet.navBarContainer)
        style {
            backgroundColor(colorMode.toSitePalette().nearBackground)
            borderBottom {
                width = 4.px
                style = LineStyle.Solid
                color = colorMode.toSitePalette().brand.primary
            }
        }
    }) {
        navItems.forEach { navItem ->
            RegularButton(
                styles = listOf(AppStyles.siteStyleSheet.displayNoneMax640pxMediaQuery),
                text = navItem.title,
                isSelected = selectedButton == navItem,
                onClick = {
                    selectedButton = navItem
                    ctx.router.navigateTo(navItem.target)
                }
            )
        }
        IconButton(
            styles = listOf(AppStyles.siteStyleSheet.barsMenuClass),
            onClick = {
                isMobileMenu = !isMobileMenu
            },
            backgroundColor = Color.transparent,
            content = {
                FaIcon(
                    name = "bars",
                    modifier = Modifier.padding(top = 8.px),
                    style = IconCategory.SOLID
                )
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
    Div(attrs = {
        classes(
            if (isMobileMenu) {
                AppStyles.siteStyleSheet.mobileMenuClass
            } else {
                AppStyles.siteStyleSheet.displayNone
            }
        )
    }) {
        navItems.forEach { navItem ->
            A(
                href = BasePath.prependTo(navItem.target),
                attrs = {
                    classes(AppStyles.siteStyleSheet.mobileMenuLink)
                    style {
                        display(DisplayStyle.Block)
                        padding(10.px)
                        margin(10.px, 0.px)
                        color(colorMode.toSitePalette().siteColor)
                        backgroundColor(colorMode.toSitePalette().nearBackground)
                        textAlign("center")
                    }
                }
            ) {
                Text(navItem.title)
            }
        }
    }
}