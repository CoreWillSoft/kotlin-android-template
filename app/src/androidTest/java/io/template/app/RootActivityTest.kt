@file:Suppress("DEPRECATION")

package io.template.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import io.template.app.core.RootActivity
import io.template.domain.session.Session
import io.template.domain.session.SessionService
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.getKoin

@RunWith(AndroidJUnit4::class)
class RootActivityTest {

    @get:Rule
    var activityRule =
        object : ActivityTestRule<RootActivity>(RootActivity::class.java) {
            override fun beforeActivityLaunched() {
                runBlocking {
                    val templateApp =
                        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TemplateApp
                    templateApp
                        .getKoin()
                        .get<SessionService>()
                        .saveSession(Session(""))
                }
            }
        }

    @Test
    fun typeANumber_resultIsDisplayed() {
        onView(withId(R.id.edit_text_factorial)).perform(typeText("1"), closeSoftKeyboard())
        onView(withId(R.id.button_compute)).perform(click())

        onView(withId(R.id.text_result)).check(matches(isDisplayed()))
        onView(withId(R.id.text_result)).check(matches(withText("1")))
    }
}
