package org.example.kobwebemptyproject.components.sections

import androidx.compose.runtime.*
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
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.widgets.IconButton
import org.example.kobwebemptyproject.components.widgets.RegularButton
import org.example.kobwebemptyproject.models.ui.NavItem
import org.example.kobwebemptyproject.toSitePalette
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun NavHeader(ctx: PageContext) {
    var colorMode by ColorMode.currentState
    val navItems = listOf(
        NavItem(title = "Home", target = "/"),
        NavItem(title = "About", target = "/about"),
        NavItem(title = "Preview", target = "/preview"),
        NavItem(title = "CustomBackendDemo", target = "/custom-backend-demo"),
    )
    var selectedButton by remember {
        console.log("ctx.route.path = ${ctx.route.path}")
        val navItem: NavItem? = navItems.find { BasePath.prependTo(it.target) == ctx.route.path }
        mutableStateOf(navItem ?: navItems[0])
    }

    Row(
        modifier = Modifier
            .id("header")
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .width(100.percent)
            .padding(all = 4.px),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
        navItems.forEach { navItem ->
            RegularButton(
                text = navItem.title,
                isSelected = selectedButton == navItem,
                onClick = {
                    selectedButton = navItem
                    ctx.router.navigateTo(navItem.target)
                }
            )
        }
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