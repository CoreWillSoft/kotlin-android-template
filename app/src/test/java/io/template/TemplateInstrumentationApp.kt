package io.template

import io.template.app.TemplateApp

class TemplateInstrumentationApp : TemplateApp() {
    override val attachDiOnStart = false
}
