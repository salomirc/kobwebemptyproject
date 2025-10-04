package org.example.kobwebemptyproject.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.core.layout.Layout
import org.example.kobwebemptyproject.api_caller.WebApiCaller
import org.example.kobwebemptyproject.error_handling.ErrorHandler
import org.example.kobwebemptyproject.error_handling.ErrorHandlerBroadcastService
import org.example.kobwebemptyproject.repositories.BlogRepository
import org.example.kobwebemptyproject.use_cases.GetUsersUseCase
import org.example.kobwebemptyproject.view_models.CustomBackendDemoViewModel
import org.example.kobwebemptyproject.view_models.MainViewModel

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

    val apiCaller = WebApiCaller()

    fun provideCustomBackendDemoViewModel(): CustomBackendDemoViewModel {
        return CustomBackendDemoViewModel(
            getUsersUseCase = GetUsersUseCase(
                repository = BlogRepository(
                    apiCaller = apiCaller
                )
            ),
            errorHandler = ErrorHandler(
                viewModelName = CustomBackendDemoViewModel.TAG
            )
        )
    }

    fun provideMainViewModel(): MainViewModel {
        return MainViewModel(broadcastService = ErrorHandlerBroadcastService)
    }
}