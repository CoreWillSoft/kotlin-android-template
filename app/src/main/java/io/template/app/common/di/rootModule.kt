package io.template.app.common.di

import io.template.app.core.RootActivity
import io.template.app.core.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    viewModel { RootViewModel(get(), get()) }
    scope<RootActivity> {
    }
}
