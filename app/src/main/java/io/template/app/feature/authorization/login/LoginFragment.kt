package io.template.app.feature.authorization.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import io.template.app.R
import io.template.app.common.ui.BaseFragment
import io.template.app.common.ui.viewLifecycleLazy
import io.template.app.databinding.LoginLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(R.layout.login_layout) {

    private val binding by viewLifecycleLazy { LoginLayoutBinding.bind(requireView()) }
    private val vm by viewModel<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginDisclaimer.text = vm.text
        binding.loginDisclaimer.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toRegistration())
        }
    }
}
