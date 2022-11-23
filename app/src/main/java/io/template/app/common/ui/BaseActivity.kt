package io.template.app.common.ui

import android.os.Bundle
import org.koin.androidx.scope.ScopeActivity

/** Base Activity to inject logic in-the-middle, e.g.: loggers, debuggers, extra dev. view, etc. */
abstract class BaseActivity : ScopeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
