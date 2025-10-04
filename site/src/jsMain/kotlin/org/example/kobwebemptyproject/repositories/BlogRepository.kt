package org.example.kobwebemptyproject.repositories

import com.varabyte.kobweb.browser.http.HttpMethod
import kotlinx.browser.window
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import org.example.kobwebemptyproject.api_caller.IWebApiCaller
import org.example.kobwebemptyproject.api_caller.fetchResponse
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
//            apiCaller.invoke {
////                window.fetchResponse(HttpMethod.GET, resource = "/usersss".toEndpointUrl())
//                window.fetchResponse(HttpMethod.GET, resource = "/users".toEndpointUrl())
//            }
            apiCaller.raw {
                window.fetchResponse(HttpMethod.GET, resource = "/usersss".toEndpointUrl())
//                window.fetchResponse(HttpMethod.GET, resource = "/users".toEndpointUrl())
            }
                .mapCatching { apiSuccess ->
                    console.log("ApiCallerLog: API response: $apiSuccess")
                    console.log("ApiCallerLog: API response HTTP Code: ${apiSuccess.code}")
                    console.log("ApiCallerLog: API response Header \"Content-Type\", \"${apiSuccess.headers.get("Content-Type")}\"")
                    Json.decodeFromString<List<UserResponseDto>>(apiSuccess.body)
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