package io.template.domain

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapError
import java.io.IOException

typealias DomainResult<V> = Result<V, DomainError>

sealed class DomainError(cause: Throwable?) : Exception(cause) {
    class GenericError(cause: Throwable?) : DomainError(cause)
    class NetworkError(cause: Throwable?) : DomainError(cause)
}

internal fun <V, E : Throwable> Result<V, E>.mapDomainError(): DomainResult<V> {
    return mapError {
        when (it) {
            is IOException -> DomainError.NetworkError(it.cause)
            else -> DomainError.GenericError(it.cause)
        }
    }
}
