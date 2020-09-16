package io.template.app.rule

import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import io.template.app.R
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

inline fun <reified F : Fragment> defaultFragmentInContainerRule(
    @StyleRes themeResId: Int = R.style.Theme_App,
    fragmentArgs: Bundle? = null,
    factory: FragmentFactory? = null
) = FragmentInContainerRule(F::class.java, themeResId, fragmentArgs, factory)

class FragmentInContainerRule<F : Fragment>(
    private val fragmentClass: Class<F>,
    @StyleRes private val themeResId: Int,
    private val fragmentArgs: Bundle? = null,
    private val factory: FragmentFactory? = null
) : TestRule {

    lateinit var scanario: FragmentScenario<F>

    override fun apply(base: Statement, description: Description) = object : Statement() {
        override fun evaluate() {
            scanario =
                FragmentScenario.launchInContainer(fragmentClass, fragmentArgs, themeResId, factory)
            base.evaluate()
        }
    }
}
