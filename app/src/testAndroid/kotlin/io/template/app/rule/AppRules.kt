package io.template.app.rule

import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import com.schibsted.spain.barista.rule.cleardata.ClearFilesRule
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule
import com.schibsted.spain.barista.rule.flaky.FlakyTestRule
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
