package io.template.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.IsolationMode
import io.kotest.extensions.junitxml.JunitXmlReporter

object KotestConfig : AbstractProjectConfig() {

    override val isolationMode: IsolationMode = IsolationMode.InstancePerLeaf

    override fun extensions(): List<Extension> = listOf(
        JunitXmlReporter(
            includeContainers = false,
            useTestPathAsName = true,
            outputDir = "test-results/excludeContainers"
        )
    )
}
