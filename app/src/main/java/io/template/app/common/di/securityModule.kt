package io.template.app.common.di

import io.template.app.common.security.SecurityProvider
import io.template.app.common.security.SecurityProviderImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val securityModule = module {
    factory<SecurityProvider> { SecurityProviderImpl(androidApplication()) }
}
