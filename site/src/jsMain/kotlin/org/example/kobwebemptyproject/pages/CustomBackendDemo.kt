package org.example.kobwebemptyproject.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.http.http
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import org.example.kobwebemptyproject.models.UserModel
import org.example.kobwebemptyproject.models.UserResponseDto
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

fun String.toEndpointUrl() = "$BASE_URL$this"

@Page
@Composable
@Layout(".components.layouts.PageMainLayout")
fun CustomBackendDemoPage() {
    var users by remember { mutableStateOf<List<UserModel>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            //trigger deserialization error
            //val response = window.http.get(resource = "/posts".toEndpointUrl()).decodeToString()
            val response = window.http.get(resource = "/users".toEndpointUrl()).decodeToString()
            val usersList = Json.decodeFromString<List<UserResponseDto>>(response)
            users = usersList.map { it.toDomainModel() }
        } catch (e: Exception) {
            console.error("Failed to fetch users", e)
            error = "Could not load users. Please try again later."
        }
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
