package org.example.kobwebemptyproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.vh

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        val colorMode = ColorMode.current
        LaunchedEffect(colorMode) { // Relaunched every time the color mode changes
            localStorage.setItem("color-mode", colorMode.name)
        }

        // A full screen Silk surface. Sets the background based on Silk's palette
        // and animates color changes.
        Surface(SmoothColorStyle
            .toModifier()
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .minHeight(100.vh)
        ) {
            content()
        }
    }
}

