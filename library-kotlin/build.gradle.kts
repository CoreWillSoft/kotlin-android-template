version = LibraryKotlinCoordinates.LIBRARY_VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
    id("org.jetbrains.dokka") version BuildPluginsVersions.DOKKA
}

dependencies {
    implementation(Dependencies.Core.KOTLIN_COROUTINES)
    testImplementation(Dependencies.Testing.JUNIT)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
