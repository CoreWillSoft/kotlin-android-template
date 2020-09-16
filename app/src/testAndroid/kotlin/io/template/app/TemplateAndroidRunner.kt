package io.template.app

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.linkedin.android.testbutler.TestButler

class TemplateAndroidRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TemplateTestApp::class.java.name, context)
    }

    override fun onStart() {
        TestButler.setup(targetContext)
        super.onStart()
    }

    override fun finish(resultCode: Int, results: Bundle) {
        TestButler.teardown(targetContext)
        super.finish(resultCode, results)
    }
}
