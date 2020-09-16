// ktlint-disable filename
@file:Suppress("DEPRECATION")

package io.template.app.rule

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import io.template.app.TemplateApp
import io.template.app.core.RootActivity
import io.template.app.stub.stubSession
import io.template.domain.session.SessionService
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.getKoin

class RootActivityTestRule(
    private val applyStubSession: Boolean = true
) : ActivityTestRule<RootActivity>(RootActivity::class.java) {

    override fun launchActivity(startIntent: Intent?): RootActivity {
        return super.launchActivity(
            Intent(Intent.ACTION_MAIN).apply {
                // putExtra(RootActivity.STARTED_FROM_INSTRUMENTAL_TESTS, true)
            }
        )
    }

    override fun beforeActivityLaunched() {
        if (applyStubSession) {
            runBlocking {
                val app = ApplicationProvider.getApplicationContext<TemplateApp>()
                app.getKoin()
                    .get<SessionService>()
                    .saveSession(stubSession)
            }
        }
    }

    override fun afterActivityFinished() {
    }
}
