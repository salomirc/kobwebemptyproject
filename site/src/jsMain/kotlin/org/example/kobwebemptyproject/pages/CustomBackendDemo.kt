package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import org.example.kobwebemptyproject.components.layouts.AppContainerLayoutScope
import org.example.kobwebemptyproject.models.domain.UserModel
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

@Page
@Composable
@Layout(".components.layouts.PageMainLayout")
fun AppContainerLayoutScope.CustomBackendDemoPage() {
    var users by remember { mutableStateOf<List<UserModel>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val viewModel = remember { this.provideCustomBackendDemoViewModel() }

    LaunchedEffect(Unit) {
        viewModel.getUsers()?.let { users = it } ?: run { error = "Failed to fetch users" }
    }

    Div(attrs = {
        style { padding(20.px) }
    }) {
        H3 { Text("Custom Backend Demo") }
        Text("The following users were retrieved from the backend: ")
        users?.let { userModels ->
            Ul {
                if (userModels.isEmpty()) {
                    Text("No users found.")
                } else {
                    userModels.forEach { user -> UserItem(user) }
                }
            }
        } ?: Text("Loading users...")
        error?.let { errorMsg -> Div { Text(errorMsg) } }
    }

    H5 { Text("Random number: ${viewModel.randomNUmber}")}
    H5 { Text("Random number: ${viewModel.randomNUmber}")}
}

@Composable
fun UserItem(user: UserModel) {
    Li {
        Span(attrs = {
            style {
                fontWeight("bold")
            }
        }) { Text(user.name) }
        Text(" (${user.email})")
    }
}
