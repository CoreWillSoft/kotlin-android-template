package io.template.app.common.di

import io.template.app.core.RootActivity
import io.template.app.core.RootViewModel
import io.template.domain.ActivityScopeExample
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    scope<RootActivity> {
        viewModel { RootViewModel(get(), get()) }
        scoped { ActivityScopeExample(get()) }
    }
}
