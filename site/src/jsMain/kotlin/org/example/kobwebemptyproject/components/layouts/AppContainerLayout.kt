package org.example.kobwebemptyproject.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.core.layout.Layout
import org.example.kobwebemptyproject.di.AppContainerLayoutScope

@Layout
@Composable
fun AppContainerLayout(content: @Composable AppContainerLayoutScope.() -> Unit) {
    val scope = remember { AppContainerLayoutScope() }

    scope.content()
}