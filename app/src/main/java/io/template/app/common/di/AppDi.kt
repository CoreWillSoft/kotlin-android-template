package io.template.app.common.di

import io.template.app.BuildConfig
import io.template.app.TemplateApp
import io.template.app.common.boot.LoggerInitializer
import io.template.app.feature.authorization.authorisationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun TemplateApp.attachDi() {
    startKoin(appDeclaration = diDeclaration())
}

internal fun TemplateApp.diDeclaration(): KoinAppDeclaration = {
    androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
    androidContext(this@diDeclaration)
    modules(
        /*region Root Modules*/
        startupModule,
        rootModule,
        coroutinesModule,
        /*endregion*/
        /*region IO*/
        securityModule,
        serializerModule,
        /*endregion*/
        /*region Core Domain*/
        sessionModule,
        authorisationModule
        /*endregion*/
    )
}

val startupModule = module(createdAtStart = true) {
    single { LoggerInitializer(BuildConfig.DEBUG) }
}
