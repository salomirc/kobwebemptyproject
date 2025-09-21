package org.example.kobwebemptyproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.animation.TransitionDurationVars
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.loadFromLocalStorage
import com.varabyte.kobweb.silk.theme.colors.saveToLocalStorage
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.css.percent

private const val COLOR_MODE_KEY = "kobwebdemoproject:colorMode"

@InitSilk
fun initColorMode(ctx: InitSilkContext) {
    ctx.config.initialColorMode = ColorMode.loadFromLocalStorage(COLOR_MODE_KEY) ?: ColorMode.systemPreference
}

@InitSilk
fun initStyles(ctx: InitSilkContext) {
    ctx.stylesheet.apply {
        registerStyleBase("html, body") { Modifier.fillMaxHeight() }
        registerStyleBase("body") { Modifier.scrollBehavior(ScrollBehavior.Smooth) }
    }
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        val colorMode = ColorMode.current
        LaunchedEffect(colorMode) {
            colorMode.saveToLocalStorage(COLOR_MODE_KEY)
        }

        AppStyles.siteStyleSheet = SiteStyleSheet(colorMode.toSitePalette())
        Style(AppStyles.siteStyleSheet)

//        Surface(SmoothColorStyle.toModifier().fillMaxHeight()) {
        Surface(
            modifier = FastColorStyle
                .toModifier()
                .minHeight(100.percent)
                .scrollBehavior(ScrollBehavior.Smooth)
        ) {
            content()
        }
    }
}

val FastColorTransitionDurationVar by StyleVariable(
    prefix = "silk",
    defaultFallback = TransitionDurationVars.Fast.value()
)

val FastColorStyle = CssStyle.base {
    Modifier.transition(Transition.of("background-color", FastColorTransitionDurationVar.value()))
}

