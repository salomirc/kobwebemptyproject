package org.example.kobwebemptyproject.repositories

import com.varabyte.kobweb.browser.http.http
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import org.example.kobwebemptyproject.helpers.toEndpointUrl
import org.example.kobwebemptyproject.models.data.UserResponseDto
import org.example.kobwebemptyproject.models.domain.UserModel

class BlogRepository {

    suspend fun getUsers(): List<UserModel>? {
        try {
            //trigger deserialization error
            //val response = window.http.get(resource = "/posts".toEndpointUrl()).decodeToString()
            val response = window.http.get(resource = "/users".toEndpointUrl()).decodeToString()
            val usersList = Json.decodeFromString<List<UserResponseDto>>(response)
            val users = usersList.map { it.toDomainModel() }
            return users
        } catch (e: Exception) {
            console.error("Failed to fetch users", e)
        }
        return null
    }
}