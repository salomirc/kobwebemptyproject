package org.example.kobwebemptyproject.repositories

import com.varabyte.kobweb.browser.http.http
import kotlinx.browser.window
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import org.example.kobwebemptyproject.components.api_caller.IWebApiCaller
import org.example.kobwebemptyproject.helpers.toEndpointUrl
import org.example.kobwebemptyproject.models.data.UserResponseDto
import org.example.kobwebemptyproject.models.domain.UserModel
import org.example.kobwebemptyproject.repositories.ResponseState.ActiveResponseState

interface IBlogRepository {
    suspend fun getUsers(): Flow<ActiveResponseState<List<UserModel>>>
}

class BlogRepository(
    private val apiCaller: IWebApiCaller
): IBlogRepository {

    override suspend fun getUsers(): Flow<ActiveResponseState<List<UserModel>>> {
        return DataSourcePattern.singlePattern {
            apiCaller.invoke {
                window.http.get(resource = "/users".toEndpointUrl())
            }
                .mapCatching { bytes ->
                    Json.decodeFromString<List<UserResponseDto>>(bytes.decodeToString())
                }
                .mapCatching { userResponseDtos ->
                    userResponseDtos.map(Mapper::mapToDomainUser)
                }
        }
    }

    private object Mapper {
        fun mapToDomainUser(result: UserResponseDto): UserModel {
            // filter results + conversion from dto to a domain model
            return result.toDomainModel()
        }
    }
}