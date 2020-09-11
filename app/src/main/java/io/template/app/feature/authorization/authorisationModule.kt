package io.template.app.feature.authorization

import io.template.app.feature.authorization.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authorisationModule = module {
    viewModel { LoginViewModel() }
}
