package io.template.data.session

import android.content.SharedPreferences
import androidx.core.content.edit
import io.template.app.common.security.SecurityProvider
import io.template.domain.session.Session
import io.template.domain.session.SessionDataSource
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SessionDataSourceImpl(
    private val securityProvider: SecurityProvider,
    private val json: Json
) : SessionDataSource {

    private val preferences: SharedPreferences by lazy {
        securityProvider.createDefaultSharedPrefs(PREFS_FILE)
    }

    override var session: Session?
        get() = preferences.getString(SESSION_KEY, null)
            ?.let { json.decodeFromString<SessionData>(it) }
            ?.let { it.toDomain() }
        set(value) = preferences.edit {
            val serializedValue = value.toData()?.let { json.encodeToString(it) }
            putString(SESSION_KEY, serializedValue)
        }

    companion object {
        private const val PREFS_FILE = "session_preferences"
        private const val SESSION_KEY = "session_key"
    }
}

@Serializable
data class SessionData(val token: String)

internal fun SessionData.toDomain() = Session(token)
internal fun Session?.toData() = this?.let { SessionData(token) }
