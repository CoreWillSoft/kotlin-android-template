package io.template.app.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.template.app.common.navigation.AppRoutes.Companion.ROOT
import io.template.app.feature.authorization.authorizationGraph
import io.template.app.feature.dashboard.dashboardGraph

class AppRoutes private constructor() {
    companion object {
        const val ROOT = "root"
        const val DASHBOARD_MAIN = "dashboard_main"
        const val DASHBOARD = "dashboard"
        const val LOGIN = "login"
        const val REGISTRATION = "registration"
    }
}

@Composable
fun rememberAppNavController(): NavHostController = rememberNavController().apply {
    NavHost(navController = this, startDestination = ROOT) {
        composable(ROOT) { }
        authorizationGraph(this@apply)
        dashboardGraph()
    }
}
