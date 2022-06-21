package io.template.app.rule

import com.adevinta.android.barista.rule.cleardata.ClearDatabaseRule
import com.adevinta.android.barista.rule.cleardata.ClearFilesRule
import com.adevinta.android.barista.rule.cleardata.ClearPreferencesRule
import com.adevinta.android.barista.rule.flaky.FlakyTestRule
import org.junit.rules.RuleChain

fun defaultAppRule(defaultFlakyAttempts: Int = 1, clearAllData: Boolean = true) =
    RuleChain.outerRule(FlakyTestRule().allowFlakyAttemptsByDefault(defaultFlakyAttempts)).run {
        if (clearAllData) {
            around(ClearPreferencesRule())
                .around(ClearDatabaseRule())
                .around(ClearFilesRule())
        } else {
            this
        }
    }
