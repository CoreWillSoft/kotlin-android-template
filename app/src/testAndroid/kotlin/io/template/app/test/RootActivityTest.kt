package io.template.app.test

import androidx.compose.ui.test.junit4.createComposeRule
import io.template.app.rule.defaultAppRule
import org.junit.Rule
import org.junit.Test

class RootActivityTest {

    @get:Rule
    internal val rule = defaultAppRule()
//        .around(RootActivityTestRule(applyStubSession = true))

    @get:Rule
    val composeActivity = createComposeRule()

    @Test
    fun rootNavigation_withSession_displaysDashboard() {
//        composeActivity.setContent {
//            DashboardScreen()
//        }
//        composeActivity.onNodeWithText("Compute").assertIsDisplayed()
//        onScreen<DashboardScreen> {
//            factorial {
//                typeText("1")
//                ViewActions.closeSoftKeyboard()
//            }
//            compute { click() }
//            result {
//                isVisible()
//                hasText("1")
//            }
//        }
    }
}
