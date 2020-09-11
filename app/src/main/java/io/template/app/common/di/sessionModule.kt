package io.template.app.common.di

import io.template.data.session.SessionDataSourceImpl
import io.template.domain.session.SessionDataSource
import io.template.domain.session.SessionService
import io.template.domain.session.SessionServiceImpl
import org.koin.dsl.module

val sessionModule = module {
    factory<SessionDataSource> { SessionDataSourceImpl(get(), get()) }
    single<SessionService> { SessionServiceImpl(get()) }
}
