package io.template.app.core

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.babylon.orbit2.livedata.sideEffect
import io.template.app.R
import io.template.app.common.logger.AppLogger
import io.template.app.common.ui.BaseActivity
import io.template.app.databinding.RootLayoutBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class RootActivity : BaseActivity() {

    private lateinit var binding: RootLayoutBinding
    private val viewModel by stateViewModel<RootViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RootLayoutBinding.inflate(layoutInflater).apply { setContentView(root) }
        AppLogger.d("Hello motherfuckers")
        attachSession()
    }

    private fun attachSession() {
        viewModel.container.sideEffect.observe(this) {
            val navGraphRes = when (it) {
                RootEffect.GoAuthorize -> R.navigation.auth_nav_graph
                RootEffect.GoDashboard -> R.navigation.main_nav_graph
            }
            val navHostFragment = NavHostFragment.create(navGraphRes)
            supportFragmentManager.beginTransaction()
                .add(binding.navHostContainer.id, navHostFragment)
                .setPrimaryNavigationFragment(navHostFragment)
                .commitNow()
        }
    }
}
