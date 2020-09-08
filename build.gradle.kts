import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${BuildPluginsVersions.AGP}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildPluginsVersions.KOTLIN}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Dependencies.Presentation.Navigation.VERSION}")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version BuildPluginsVersions.DETEKT
    id("org.jlleitschuh.gradle.ktlint") version BuildPluginsVersions.KTLINT.PLUGIN
    id("com.github.ben-manes.versions") version BuildPluginsVersions.DEPENDENCY_UPDATES
}

/* Shared Infrastructure */

allprojects {
    group = PUBLISHING_GROUP
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    ktlint {
        debug.set(false)
        version.set(BuildPluginsVersions.KTLINT.CONFIG)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    detekt {
        config = rootProject.files("quality/detekt/detekt.yml")
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}

tasks.withType<DependencyUpdatesTask> {
    fun isNonStable(version: String) = "^[0-9,.v-]+(-r)?$".toRegex().matches(version).not()
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
