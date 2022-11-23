package io.template.domain

import android.app.Activity

class ActivityScopeExample(
    private val activity: Activity
) {
    fun runExample() {
        activity.windowManager
    }
}
