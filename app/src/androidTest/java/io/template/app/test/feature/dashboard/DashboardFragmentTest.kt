package io.template.app.test.feature.dashboard

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.action.ViewActions
import com.agoda.kakao.screen.Screen.Companion.onScreen
import io.template.app.R
import io.template.app.feature.dashboard.DashboardFragment
import io.template.app.rule.defaultAppRule
import io.template.app.rule.defaultFragmentInContainerRule
import io.template.app.screen.DashboardScreen
import org.junit.Rule
import org.junit.Test

class DashboardFragmentTest {

    @get:Rule
    val defaultRule = defaultAppRule().around(
        defaultFragmentInContainerRule<DashboardFragment>()
    )

    @Test
    fun viewsAreShownAndValid() {
        launchFragmentInContainer<DashboardFragment>(themeResId = R.style.Theme_App)
        onScreen<DashboardScreen> {
            title.isVisible()
            description.isVisible()
            factorial.hasEmptyText()
        }
    }

    @Test
    fun factorialResultIsCalculated() {
        onScreen<DashboardScreen> {
            factorial {
                typeText("1")
                ViewActions.closeSoftKeyboard()
            }
            compute { click() }
            result {
                isVisible()
                hasText("1")
            }
        }
    }
}
