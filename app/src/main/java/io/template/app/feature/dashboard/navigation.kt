package io.template.app.feature.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.template.app.common.navigation.AppRoutes.Companion.DASHBOARD
import io.template.app.common.navigation.AppRoutes.Companion.DASHBOARD_MAIN

fun NavGraphBuilder.dashboardGraph() {
    navigation(startDestination = DASHBOARD_MAIN, route = DASHBOARD) {
        composable(route = DASHBOARD_MAIN) { DashboardScreen() }
    }
}
