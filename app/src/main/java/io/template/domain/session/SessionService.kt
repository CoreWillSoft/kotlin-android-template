package io.template.domain.session

interface SessionService {

    suspend fun sessionExists(): Boolean
    suspend fun saveSession(session: Session)
}

class SessionServiceImpl(private val sessionDataSource: SessionDataSource) : SessionService {

    override suspend fun sessionExists(): Boolean {
        return sessionDataSource.session != null
    }

    override suspend fun saveSession(session: Session) {
        sessionDataSource.session = session
    }
}
