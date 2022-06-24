package io.template.app.test.feature.dashboard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import io.template.app.feature.dashboard.DashboardScreen
import io.template.app.rule.defaultAppRule
import org.junit.Rule
import org.junit.Test

class DashboardFragmentTest {

    @get:Rule(order = 0)
    internal val defaultRule = defaultAppRule()

    @get:Rule(order = 1)
    internal val composeActivity = createComposeRule()

    @Test
    fun viewsAreShownAndValid() {
        composeActivity.setContent {
            DashboardScreen()
        }
        composeActivity.onNodeWithTag("title").assertIsDisplayed()
        composeActivity.onNodeWithTag("description").assertIsDisplayed()
        composeActivity.onNodeWithTag("input").assertIsDisplayed()
        composeActivity.onNodeWithTag("factorial").assertIsDisplayed()
    }

    @Test
    fun factorialResultIsCalculated() {
        composeActivity.setContent {
            DashboardScreen()
        }
        composeActivity.onNodeWithTag("input").performTextInput("1")

        composeActivity.onNodeWithTag("compute").performClick()
        composeActivity.onNodeWithTag("factorial").assertIsDisplayed()
        composeActivity.onNodeWithTag("factorial").assertTextEquals("1")
    }
}
