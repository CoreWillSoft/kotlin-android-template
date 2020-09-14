package io.template.app

import org.koin.android.ext.android.getKoin
import org.koin.core.module.Module

class TemplateTestApp : TemplateApp() {

    internal fun withModules(vararg module: Module, run: () -> Unit) {
        with(getKoin()) {
            loadModules(module.toList())
            run()
            unloadModules(module.toList())
        }
    }
}
