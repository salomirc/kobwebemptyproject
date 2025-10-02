package org.example.kobwebemptyproject.components.api_caller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * An interface for making API calls for services returning a Retrofit [Response].
 * It is used to abstract away the implementation of the API call and to wrap the call in order to provide unified
 * (or even centralized, if desired) error handling.
 */
interface IWebApiCaller {

    /**
     * Converts a [T] to [Result] containing the body of type [T].
     */
    suspend fun <T : Any> invoke(
        callBlock: suspend () -> T
    ): Result<T>

    /**
     * Converts a [Unit] to [Result] containing the body of type [Unit].
     */
    suspend fun invokeUnit(
        callBlock: suspend () -> Unit
    ): Result<Unit>

    /**
     * Converts a [T] nullable to [Result] containing the body of type [T] nullable.
     */
    suspend fun <T> invokeNullable(
        callBlock: suspend () -> T?
    ): Result<T?>
}

/**
 * Creates an [WebApiCaller] that can be used to make calls for API services.
 * All exceptions are caught and propagated to [Result].
 */
class WebApiCaller() : IWebApiCaller {

    override suspend fun <T : Any> invoke(callBlock: suspend () -> T): Result<T> {
        return networkCallHandling(
            callBlock = callBlock,
            handleResponseBody = { it ?: throw NoResponseBodyException(NOT_NULL_BODY_EXPECTED_MESSAGE) }
        )
    }

    override suspend fun invokeUnit(callBlock: suspend () -> Unit): Result<Unit> {
        return networkCallHandling(
            callBlock = callBlock,
            handleResponseBody = { it ?: Unit }
        )
    }

    override suspend fun <T> invokeNullable(callBlock: suspend () -> T?): Result<T?> {
        return networkCallHandling(
            callBlock = callBlock,
            handleResponseBody = { it }
        )
    }

    private suspend fun <T> networkCallHandling(
        callBlock: suspend () -> T,
        handleResponseBody: (data: T?) -> T
    ): Result<T> {
        return runCatching {
            val response = withContext(Dispatchers.Default) {
                callBlock()
            }
            handleResponseBody(response)
        }.onFailure { t ->
            console.error("ApiCallerLog: Exception on API call:", t)
        }
    }

    companion object {
        const val NOT_NULL_BODY_EXPECTED_MESSAGE = "Not null body response was expected!"
    }
}

/**
 * Exception thrown when the API call returns an empty response body, but a body is expected.
 *
 * @param message exception message
 */
class NoResponseBodyException(message: String) : RuntimeException(message)