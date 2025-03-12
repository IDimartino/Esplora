package com.esplora.app.core.data.remote.networking

import com.esplora.app.core.domain.util.NetworkError
import com.esplora.app.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        println("UnresolvedAddressException: ${e.message}")
        return Result.Error(NetworkError.NO_INTERNET)
    } catch(e: SerializationException) {
        println("SerializationException: ${e.message}")
        return Result.Error(NetworkError.SERIALIZATION)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        println("UnknownException: ${e.message}")
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}