package io.template.app.feature.authorization.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.template.app.R
import io.template.app.common.ui.BaseFragment
import io.template.app.common.ui.UiUtil
import io.template.app.common.ui.viewLifecycleLazy
import io.template.app.core.RootViewModel
import io.template.app.databinding.LoginLayoutBinding
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.ldralighieri.corbind.widget.textChanges

class LoginFragment : BaseFragment(R.layout.login_layout) {

    private val binding by viewLifecycleLazy { LoginLayoutBinding.bind(requireView()) }
    private val vm by stateViewModel<LoginViewModel>()
    private val rootVm by sharedViewModel<RootViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.container.stateFlow
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        vm.container.sideEffectFlow
            .onEach { it.also { renderEffect(it) } }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        bindViews()
    }

    private fun bindViews() {
        binding.username.textChanges()
            .debounce(UiUtil.INPUT_DEBOUNCE_DELAY)
            .onEach {
                vm.onInputChange(vm.container.stateFlow.value.input.copy(username = it.toString()))
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        binding.password.textChanges()
            .debounce(UiUtil.INPUT_DEBOUNCE_DELAY)
            .onEach {
                vm.onInputChange(vm.container.stateFlow.value.input.copy(password = it.toString()))
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        binding.tryLogin.setOnClickListener { vm.onLogin() }
        binding.unregistered.setOnClickListener { vm.onRegister() }
    }

    private fun renderState(state: State) {
        with(state.inputValidity) {
            binding.tryLogin.isEnabled = isValid
            binding.usernameWrapper.error =
                if (usernameValid == false) getString(R.string.login_username_invalid_hint)
                else null
            binding.passwordWrapper.error =
                if (passwordValid == false) getString(R.string.login_password_invalid_hint)
                else null
        }
    }

    private fun renderEffect(effect: Effect) {
        when (effect) {
            Effect.GoRegister -> {
                findNavController().navigate(LoginFragmentDirections.toRegistration())
            }
            Effect.Authorized -> {
                rootVm.onAuthorized()
            }
            else -> Unit
        }
    }
}
