package io.template.app.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import io.template.app.common.navigation.AppRoutes.Companion.DASHBOARD
import io.template.app.common.navigation.AppRoutes.Companion.LOGIN
import io.template.app.common.navigation.rememberAppNavController
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun RootScreen(
    navigationController: NavController = rememberAppNavController(),
    viewModel: RootViewModel = getViewModel()
) {
    viewModel.collectSideEffect { effect ->
        val route = when (effect) {
            RootEffect.GoAuthorize -> LOGIN
            RootEffect.GoDashboard -> DASHBOARD
        }
        navigationController.navigate(route)
    }
}
