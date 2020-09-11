package io.template.app

import android.app.Application
import io.template.app.common.di.attachDi

class TemplateApp : Application() {

    override fun onCreate() {
        super.onCreate()
        attachDi()
    }
}
