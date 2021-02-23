package io.template.domain.auth

import com.github.michaelbull.result.runCatching
import io.template.domain.DomainResult
import io.template.domain.mapDomainError
import io.template.domain.session.Session
import io.template.domain.session.SessionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

interface AuthorizationService {
    suspend fun signin(credentials: SigninCredentials): DomainResult<Unit>
}

data class SigninCredentials(val username: String, val password: String)

@Suppress("MagicNumber")
class AuthorizationServiceImpl(
    val appScope: CoroutineScope,
    private val sessionService: SessionService
) : AuthorizationService {

    override suspend fun signin(credentials: SigninCredentials): DomainResult<Unit> = runCatching {
        delay(3000L)
        sessionService.saveSession(Session("sample token"))
        Unit
    }.mapDomainError()
}
