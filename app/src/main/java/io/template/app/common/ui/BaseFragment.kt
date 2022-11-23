package io.template.app.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import org.koin.androidx.scope.ScopeFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) : ScopeFragment(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Suppress("MaxLineLength")
fun <T> Fragment.viewLifecycle(
    bindUntilEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, LifecycleEventObserver {

        // A backing property to hold our value
        private var binding: T? = null

        private var viewLifecycleOwner: LifecycleOwner? = null

        init {
            // Observe the View Lifecycle of the Fragment
            this@viewLifecycle
                .viewLifecycleOwnerLiveData
                .observe(this@viewLifecycle) { newLifecycleOwner ->
                    viewLifecycleOwner
                        ?.lifecycle
                        ?.removeObserver(this)

                    viewLifecycleOwner = newLifecycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                }
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == bindUntilEvent) {
                // Clear out backing property just before onDestroyView
                binding = null
            }
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            // Return the backing property if it's set
            return this.binding!!
        }

        override fun setValue(
            thisRef: Fragment,
            property: KProperty<*>,
            value: T
        ) {
            // Set the backing property
            this.binding = value
        }
    }

fun <T> Fragment.viewLifecycleLazy(initialise: () -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

        // A backing property to hold our value
        private var binding: T? = null

        private var viewLifecycleOwner: LifecycleOwner? = null

        init {
            // Observe the View Lifecycle of the Fragment
            this@viewLifecycleLazy
                .viewLifecycleOwnerLiveData
                .observe(this@viewLifecycleLazy) { newLifecycleOwner ->
                    viewLifecycleOwner
                        ?.lifecycle
                        ?.removeObserver(this)

                    viewLifecycleOwner = newLifecycleOwner.also {
                        it.lifecycle.addObserver(this)
                    }
                }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            binding = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            // Return the backing property if it's set, or initialise
            return this.binding ?: initialise().also {
                this.binding = it
            }
        }
    }
