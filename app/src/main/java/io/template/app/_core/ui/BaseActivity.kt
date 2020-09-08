package io.template.app._core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** Base Activity to inject logic in-the-middle, e.g.: loggers, debuggers, extra dev. view, etc. */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
