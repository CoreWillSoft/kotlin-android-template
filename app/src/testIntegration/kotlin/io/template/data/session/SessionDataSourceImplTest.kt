package io.template.data.session

import androidx.core.content.edit
import androidx.test.core.app.ApplicationProvider
import com.appmattus.kotlinfixture.kotlinFixture
import io.template.app.TemplateApp
import io.template.app.TemplateInstrumentationApp
import io.template.app.common.di.diDeclaration
import io.template.app.common.security.SecurityProvider
import io.template.data.test.fake.keystore.RobolectricKeyStore
import io.template.domain.session.Session
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = TemplateInstrumentationApp::class)
class SessionDataSourceImplTest : KoinTest {

    companion object {
        @JvmStatic
        @BeforeClass
        fun beforeClass() {
            RobolectricKeyStore.setup
        }

        private const val PREFS_FILE = "session_preferences"
        private const val SESSION_KEY = "session_key"
    }

    @get:Rule
    val diRule = KoinTestRule.create(
        ApplicationProvider.getApplicationContext<TemplateApp>().diDeclaration()
    )

    private val securityProvider: SecurityProvider by inject()
    private val json: Json by inject()

    private val dataSourceImpl by lazy { SessionDataSourceImpl(securityProvider, json) }
    private val sessionStub: Session by lazy { Session(token = kotlinFixture().invoke()) }

    @Test
    fun session_onAppStart_isEmpty() {
        assert(dataSourceImpl.session == null) { "Session should be null" }
    }

    @Test
    fun session_preferences_parsed_and_stored() {
        dataSourceImpl.session = sessionStub
        val sessionFromPreferences =
            securityProvider.createDefaultSharedPrefs(PREFS_FILE)
                .getString(SESSION_KEY, null)
                ?.let { json.decodeFromString<SessionData>(it).toDomain() }
        assert(sessionFromPreferences == sessionStub)
    }

    @Test
    fun session_preferences_parsed_and_read() {
        securityProvider.createDefaultSharedPrefs(PREFS_FILE).edit {
            putString(SESSION_KEY, json.encodeToString(sessionStub.toData()))
        }
        val sessionFromDataSource = dataSourceImpl.session
        assert(sessionFromDataSource == sessionStub)
    }
}
