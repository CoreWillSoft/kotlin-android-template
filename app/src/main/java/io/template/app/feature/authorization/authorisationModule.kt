package io.template.app.feature.authorization

import io.template.app.common.di.APP_COROUTINE_SCOPE
import io.template.app.feature.authorization.login.LoginFragment
import io.template.app.feature.authorization.login.LoginViewModel
import io.template.domain.auth.AuthorizationService
import io.template.domain.auth.AuthorizationServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authorisationModule = module {
    single<AuthorizationService> {
        AuthorizationServiceImpl(
            appScope = get(named(APP_COROUTINE_SCOPE)),
            sessionService = get()
        )
    }
    scope<LoginFragment> {
        viewModel { LoginViewModel(get(), get(), get()) }
    }
}
