package org.example.kobwebemptyproject.use_cases


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.example.kobwebemptyproject.models.domain.UserModel
import org.example.kobwebemptyproject.repositories.IBlogRepository
import org.example.kobwebemptyproject.repositories.ResponseState.ActiveResponseState
import org.example.kobwebemptyproject.repositories.activeResponseStateWrapper

interface IGetUsersUseCase {
    suspend fun getUsers(): Flow<ActiveResponseState<List<UserModel>>>
}

class GetUsersUseCase(
    private val repository: IBlogRepository
): IGetUsersUseCase {

    override suspend fun getUsers(): Flow<ActiveResponseState<List<UserModel>>> {
        return withContext(Dispatchers.Default) {
            repository
                .getUsers()
                .map { responseState ->
                    responseState.activeResponseStateWrapper { users ->
                        users.map { user ->
                            user.copy(
                                name = user.name.uppercase()
                            )
                        }
                    }
                }
        }
    }
}