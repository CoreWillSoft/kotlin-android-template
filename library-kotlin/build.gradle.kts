version = LibraryKotlinCoordinates.LIBRARY_VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
    id("org.jetbrains.dokka") version BuildPluginsVersions.DOKKA
}

dependencies {
    implementation(Deps.Core.Coroutine.CORE)
    // Unit Testing
    testImplementation(Deps.Testing.Common.JUNIT)
    testImplementation(Deps.Testing.Kotest.RUNNER)
    testImplementation(Deps.Testing.Kotest.ASSERTIONS)
    testImplementation(Deps.Testing.Kotest.PROPERTY)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
