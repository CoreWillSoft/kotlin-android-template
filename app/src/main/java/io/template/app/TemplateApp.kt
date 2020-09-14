package io.template.app

import android.app.Application
import io.template.app.common.di.attachDi

open class TemplateApp : Application() {

    override fun onCreate() {
        super.onCreate()
        attachDi()
    }
}
