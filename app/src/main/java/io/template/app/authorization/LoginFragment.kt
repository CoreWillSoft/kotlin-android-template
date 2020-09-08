package io.template.app.authorization

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import io.template.app.R
import io.template.app._core.ui.BaseFragment
import io.template.app._core.ui.viewLifecycleLazy
import io.template.app.databinding.LoginLayoutBinding

class LoginFragment : BaseFragment(R.layout.login_layout) {

    private val binding by viewLifecycleLazy { LoginLayoutBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginDisclaimer.setOnClickListener { findNavController().navigate(LoginFragmentDirections.toRegistration()) }
    }
}
