package io.template.app.test

import androidx.test.espresso.action.ViewActions
import com.agoda.kakao.screen.Screen.Companion.onScreen
import io.template.app.rule.RootActivityTestRule
import io.template.app.rule.defaultAppRule
import io.template.app.screen.DashboardScreen
import org.junit.Rule
import org.junit.Test

class RootActivityTest {

    @get:Rule
    val rule = defaultAppRule().around(
        RootActivityTestRule(applyStubSession = true)
    )

    @Test
    fun rootNavigation_withSession_displaysDashboard() {
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
