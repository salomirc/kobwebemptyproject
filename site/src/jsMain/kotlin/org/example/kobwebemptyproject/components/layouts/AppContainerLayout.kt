package org.example.kobwebemptyproject.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.core.layout.Layout
import org.example.kobwebemptyproject.repositories.BlogRepository
import org.example.kobwebemptyproject.view_models.CustomBackendDemoViewModel

@Layout
@Composable
fun AppContainerLayout(content: @Composable AppContainerLayoutScope.() -> Unit) {
    val scope = remember { AppContainerLayoutScope() }

    scope.content()
}

class AppContainerLayoutScope {
    init {
        console.log("AppContainerLayoutScope init = ${this.hashCode()}")
    }

    fun provideCustomBackendDemoViewModel(): CustomBackendDemoViewModel {
        val blogRepository = BlogRepository()
        return CustomBackendDemoViewModel(blogRepository)
    }
}