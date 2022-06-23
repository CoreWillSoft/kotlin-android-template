package io.template.app.feature.authorization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.template.app.common.navigation.AppRoutes.Companion.LOGIN
import io.template.app.common.navigation.AppRoutes.Companion.REGISTRATION
import io.template.app.feature.authorization.login.LoginScreen
import io.template.app.feature.authorization.registration.RegistrationScreen

fun NavGraphBuilder.authorizationGraph(navController: NavController) {
    composable(LOGIN) { LoginScreen(navController = navController) }
    composable(REGISTRATION) { RegistrationScreen() }
}
