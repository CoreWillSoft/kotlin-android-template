package io.template.app

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import io.template.app._core.ui.BaseActivity
import io.template.app.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater).apply { setContentView(root) }
        if (savedInstanceState == null) selectNavGraph()
    }

    private fun selectNavGraph() {
        val navGraphRes = if (true) R.navigation.auth_nav_graph else R.navigation.main_nav_graph
        val navHostFragment = NavHostFragment.create(navGraphRes).apply {  }
        supportFragmentManager.beginTransaction()
            .add(binding.navHostContainer.id, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commitNow()
    }
}
