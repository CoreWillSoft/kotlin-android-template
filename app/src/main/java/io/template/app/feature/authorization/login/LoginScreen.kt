package io.template.app.feature.authorization.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import io.template.app.common.navigation.AppRoutes.Companion.DASHBOARD
import io.template.app.common.navigation.AppRoutes.Companion.REGISTRATION
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = getViewModel()
) {
    val uiState by viewModel.collectAsState()
    LoginContent(
        uiState = uiState,
        onInputChange = viewModel::onInputChange,
        onLoginClick = viewModel::onLogin,
        onRegisterClick = viewModel::onRegister
    )
    viewModel.collectSideEffect { effect ->
        when (effect) {
            Effect.GoRegister -> {
                navController.navigate(REGISTRATION)
            }
            Effect.Authorized -> {
                navController.navigate(DASHBOARD)
            }
            else -> Unit
        }
    }
}
