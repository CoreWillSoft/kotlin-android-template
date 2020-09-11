package io.template.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.listeners.Listener
import io.kotest.core.spec.IsolationMode
import io.kotest.extensions.junitxml.JunitXmlReporter

class KotestConfig : AbstractProjectConfig() {

    override val isolationMode: IsolationMode = IsolationMode.InstancePerLeaf

    override fun listeners(): List<Listener> = listOf(
        JunitXmlReporter(
            includeContainers = false,
            useTestPathAsName = true,
            outputDir = "test-results/excludeContainers"
        )
    )
}
