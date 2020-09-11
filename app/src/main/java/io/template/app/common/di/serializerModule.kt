package io.template.app.common.di

import io.template.app.common.io.createKotlinJson
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val serializerModule = module {
    single<Json> { createKotlinJson() }
}
