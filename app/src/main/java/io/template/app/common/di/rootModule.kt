package io.template.app.common.di

import androidx.lifecycle.SavedStateHandle
import io.template.app.core.RootActivity
import io.template.app.core.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    viewModel { (savedStateHandle: SavedStateHandle) -> RootViewModel(savedStateHandle, get()) }
    scope<RootActivity> {
    }
}
