package io.template.app

import android.app.Application
import io.template.app.common.di.attachDi

open class TemplateApp : Application() {

    internal open val attachDiOnStart = true

    override fun onCreate() {
        super.onCreate()
        if (attachDiOnStart) attachDi()
    }
}
