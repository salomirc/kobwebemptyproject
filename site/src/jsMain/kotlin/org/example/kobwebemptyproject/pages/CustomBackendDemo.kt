package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import org.example.kobwebemptyproject.components.layouts.AppContainerLayoutScope
import org.example.kobwebemptyproject.models.domain.UserModel
import org.example.kobwebemptyproject.repositories.ResponseState.ActiveResponseState.Failure
import org.example.kobwebemptyproject.repositories.ResponseState.ActiveResponseState.Success
import org.example.kobwebemptyproject.view_models.CustomBackendDemoViewModel
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

@Page
@Composable
@Layout(".components.layouts.PageMainLayout")
fun AppContainerLayoutScope.CustomBackendDemoPage() {
    val viewModel: CustomBackendDemoViewModel = remember { this.provideCustomBackendDemoViewModel() }
    val model by viewModel.modelStateFlow.collectAsState()
    CustomBackendDemo(
        model = model,
        processEvent = viewModel::processEvent
    )
}

@Composable
fun CustomBackendDemo(
    model: CustomBackendDemoViewModel.Model,
    processEvent: suspend (CustomBackendDemoViewModel.Event) -> Unit,
) {
    LaunchedEffect(Unit) {
        processEvent(CustomBackendDemoViewModel.Event.GetUsers)
    }

    Div(attrs = {
        style { padding(20.px) }
    }) {
        H3 { Text("Custom Backend Demo") }
        Text("The following users were retrieved from the backend: ")
        when(val responseState = model.userModelsResponseState) {
            is Success -> {
                val users = model.userModelsResponseState.data
                if (users.isEmpty()) {
                    Text("No users found.")
                } else {
                    Ul {
                        users.forEach { user ->
                            UserItem(user)
                        }
                    }
                }
            }
            is Failure -> {
                Div { Text("Failed to fetch users") }
            }
            else -> {
                Text("Loading users...")
            }
        }
    }
}

@Composable
fun UserItem(user: UserModel) {
    Li { Text("${user.name} (${user.email})") }
}
