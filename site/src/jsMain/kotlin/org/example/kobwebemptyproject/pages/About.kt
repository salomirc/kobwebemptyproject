package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.kobwebemptyproject.components.sections.Header
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun AboutPage(ctx: PageContext) {
    var colorMode by ColorMode.currentState
    Div(attrs = {
        id("container")
    }) {
        Header()
        H3 {
            Text("About Us")
        }
    }
}